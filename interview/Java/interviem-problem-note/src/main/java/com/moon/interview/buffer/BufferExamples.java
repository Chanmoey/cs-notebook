package com.moon.interview.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class BufferExamples {

    public void gen() throws IOException{
        Random r = new Random();

        var fileName = "word";

        var fout = new FileOutputStream(fileName);

        var start = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 5; j++) {
                fout.write(97 + r.nextInt(5));
            }
        }

        fout.close();
        // 21137
        System.out.println(System.currentTimeMillis() - start);
    }

    public void genUseBuffer() throws IOException{
        Random r = new Random();

        var fileName = "word";

//        var bufferSize = 16 * 1024;

        var fout = new BufferedOutputStream(new FileOutputStream(fileName));

        var start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            for (int j = 0; j < 5; j++) {
                fout.write(97 + r.nextInt(5));
            }
            fout.write(' ');
        }

        fout.close();
        // 99(1000000) 56945(1000000000)
        System.out.println(System.currentTimeMillis() - start);
    }

    public void read() throws IOException {
        var fileName = "word";
        var in = new FileInputStream(fileName);

        var start = System.currentTimeMillis();

        int b;

        while ((b = in.read()) != -1) {
        }

        System.out.println(System.currentTimeMillis() - start);
        in.close();
    }

    public void readWithBuffer() throws IOException {
        var fileName = "word";
        var in = new BufferedInputStream(new FileInputStream(fileName));

        var start = System.currentTimeMillis();

        int b;

        var bytes = new byte[1024 * 8];
        while ((b = in.read(bytes)) != -1) {
        }

        // 4431(1000000000)
        System.out.println(System.currentTimeMillis() - start);
        in.close();
    }

    public void readWithNio() throws IOException {
        var fileName = "word";

        var channel = new FileInputStream(fileName).getChannel();
        var buffer = ByteBuffer.allocate(8 * 1024);

        var start = System.currentTimeMillis();

        while ((channel.read(buffer)) != -1) {
            // channel write some to buffer, so buffer is write mode, we need to flip buffer for reading.
            buffer.flip();

            // clear buffer
            buffer.clear();

        }

        // (1000000000)
        System.out.println(System.currentTimeMillis() - start);
        channel.close();
    }

    public static void main(String[] args) throws IOException {
        BufferExamples examples = new BufferExamples();
        examples.readWithBuffer();
    }
}
