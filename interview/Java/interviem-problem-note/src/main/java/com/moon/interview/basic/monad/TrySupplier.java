package com.moon.interview.basic.monad;

public interface TrySupplier<T>{
    T get() throws Throwable;
}
