package com.moom.interviem.collectioncoding;


import java.util.*;

/**
 * @author Chanmoey
 * @date 2022年07月31日
 */
public class RandomStringGenerator<T> implements Iterable<T>{

    private final List<T> list;

    public RandomStringGenerator(List<T> list) {
        this.list = list;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public T next() {
                return list.get((int)(list.size() * Math.random()));
            }

        };
    }

    public static void main(String[] args) {
        var list = List.of("Python", "Java", "C", "Golang");
        var gen = new RandomStringGenerator<>(list);

        var it = gen.iterator();

        int python = 0;
        int java = 0;
        int c = 0;
        int golang = 0;
        for (int i = 0; i < 10000; i++) {
            switch (it.next()) {
                case "Python":
                    python++;
                    break;
                case "Java":
                    java++;
                    break;
                case "C":
                    c++;
                    break;
                case "Golang":
                    golang++;
                    break;
            }
        }
        System.out.printf("Python: {%d}, Java: {%d}, C: {%d}, Golang: {%d}.%n", python, java, c, golang);
    }
}
