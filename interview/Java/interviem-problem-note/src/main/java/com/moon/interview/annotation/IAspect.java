package com.moon.interview.annotation;

import com.moon.interview.basic.monad.Try;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public interface IAspect {

    void before();

    void after();
}
