package com.moon.interview.reflection;

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
public interface Aspect {

    void before();

    void after();

    static <T> T getProxy(Class<T> cls, String ... aspects) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var aspectInsts = Arrays.stream(aspects).map(name -> Try.ofFailable(() -> {
                    var clazz = Class.forName(name);
                    return (Aspect) clazz.getConstructor().newInstance();
                }))
                .filter(Try::isSuccess).toList();

        var inst = cls.getConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                cls.getClassLoader(),
                cls.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        for (var aspect : aspectInsts) {
                            aspect.get().before();
                        }
                        var result = method.invoke(inst);
                        for (var aspect : aspectInsts) {
                            aspect.get().after();
                        }
                        return result;
                    }
                }
        );
    }
}
