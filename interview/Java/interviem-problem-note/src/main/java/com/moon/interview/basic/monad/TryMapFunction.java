package com.moon.interview.basic.monad;

public interface TryMapFunction<T, R> {
    R apply(T t) throws Throwable;
}