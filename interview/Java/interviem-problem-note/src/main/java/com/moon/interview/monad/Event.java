package com.moon.interview.monad;

import java.util.stream.Stream;

/**
 * @author Chanmoey
 * @date 2022年08月01日
 */
public class Event<T> {

    T data;

    public Event(T data) {
        this.data = data;
    }

    static class EventData {
        Integer id;
        String msg;

        public EventData(Integer id, String msg) {
            this.id = id;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "EventData {id = " + id + ", msg = '" + msg + "'" + "}";
        }
    }

    static class Transforms {
        static  EventData transform(Integer id) {
            return switch (id) {
                case 1 -> new EventData(id, "Java");
                case 2 -> new EventData(id, "Python");
                case 3 -> new EventData(id, "Golang");
                default -> new EventData(id, "C");
            };
        }
    }

    @FunctionalInterface
    interface FN<A, B>{
        B apply(A a);
    }

    <B> Event<B> map(FN<T, B> fn) {
        return new Event<>(fn.apply(this.data));
    }

    public static void main(String[] args) {
        Stream<Event<Integer>> s= Stream.of(
                new Event<>(1),
                new Event<>(2),
                new Event<>(3),
                new Event<>(4)
        );

        s.map(event -> event.map(Transforms::transform))
                .forEach(e -> System.out.println(e.data));
    }
}
