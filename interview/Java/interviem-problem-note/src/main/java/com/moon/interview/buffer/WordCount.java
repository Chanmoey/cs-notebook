package com.moon.interview.buffer;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class WordCount {

    static final ForkJoinPool pool = ForkJoinPool.commonPool();

    /**
     * sync one thread
     * <p>
     * time: 78688ms
     */
    public static void compareWithSingle() throws IOException {
        var in = new BufferedInputStream(new FileInputStream("word"));
        var buf = new byte[4 * 1024];
        var len = 0;
        var total = new HashMap<String, Integer>();
        var startTime = System.currentTimeMillis();
        while ((len = in.read(buf)) != -1) {
            var bytes = Arrays.copyOfRange(buf, 0, len);
            var str = new String(bytes);
            var hashMap = countByString(str);
            for (var entry : hashMap.entrySet()) {
                var key = entry.getKey();
                incKey(key, total, entry.getValue());
            }
        }
        in.close();
        System.out.println("time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println(total.get("ababb"));
        System.out.println(total.size());
    }

    private static Map<String, Integer> countByString(String str) {
        var map = new HashMap<String, Integer>();
        StringTokenizer tokenizer = new StringTokenizer(str);
        while (tokenizer.hasMoreTokens()) {
            var word = tokenizer.nextToken();
            incKey(word, map, 1);
        }

        return map;
    }

    private static void incKey(String key, Map<String, Integer> map, Integer n) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + n);
        } else {
            map.put(key, n);
        }
    }

    class CountTask implements Callable<Map<String, Integer>> {

        private final String fileName;

        private final long start;

        private final long end;

        public CountTask(String fileName, long start, long end) {
            this.fileName = fileName;
            this.start = start;
            this.end = end;
        }

        @Override
        public Map<String, Integer> call() throws Exception {
            var channel = new RandomAccessFile(this.fileName, "rw").getChannel();

            // [start, end] -> Memory
            // Device -> Kernel Space -> UserSpace(buffer) -> Thread
            // Device -> Kernel Space -map-> Thread
            var mbuf = channel.map(FileChannel.MapMode.READ_ONLY, start, end - start);

            var str = StandardCharsets.US_ASCII.decode(mbuf).toString();
            channel.close();
            return countByString(str);
        }
    }

    public void run(String fileName, long chunkSize) throws ExecutionException, InterruptedException {
        var file = new File(fileName);
        var fileSize = file.length();

        long startTime = System.currentTimeMillis();

        long position = 0;
        List<ForkJoinTask<Map<String, Integer>>> taskArray = new ArrayList<>();
        while (position < fileSize) {
            long end = Math.min(position + chunkSize, fileSize);
            var result = pool.submit(new CountTask(fileName, position, end));
            position = end;
            taskArray.add(result);
        }

        Map<String, Integer> totalMap = new HashMap<>();
        for (var task : taskArray) {
            Map<String, Integer> map = task.get();
            for (var entry : map.entrySet()) {
                incKey(entry.getKey(), totalMap, entry.getValue());
            }
        }
        System.out.println("split to tasks " + taskArray.size());
        System.out.println("time: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("ababb: " + totalMap.get("ababb"));
        System.out.println("total word: " + totalMap.size());
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        WordCount wordCount = new WordCount();
        wordCount.run("word", 1024 * 1024 * 32);
//        compareWithSingle();
    }
}
