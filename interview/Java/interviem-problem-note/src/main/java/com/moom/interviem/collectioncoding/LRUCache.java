package com.moom.interviem.collectioncoding;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Chanmoey
 * @date 2022年08月01日
 */
public class LRUCache<K, V> implements Iterable<K> {

    private final int cacheSize;

    LinkedHashMap<K, V> cache = new LinkedHashMap<>();

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void cache(K key, V value) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        } else if (cache.size() >= cacheSize) {
            var it = cache.keySet().iterator();
            // remove the first element
            var first = it.next();
            cache.remove(first);
        }

        cache.put(key, value);
    }

    @Override
    public Iterator<K> iterator() {

        var it = cache.entrySet().iterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public K next() {
                return it.next().getKey();
            }
        };
    }

    public static void main(String[] args) {
        var lru = new LRUCache<String, Integer>(3);
        lru.cache("A", 1);
        lru.cache("B", 2);
        lru.cache("C", 3);
        lru.cache("D", 4);

        lru.cache("C", 10);

        System.out.println(
                "leave <-" +
                        StreamSupport.stream(lru.spliterator(), false)
                                .collect(Collectors.joining("<-"))
        );
    }
}
