package com.moon.interview.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
@SuppressWarnings("all")
public class ObjectFactory {

    public static <T> T newInstance(Class<T> clazz) throws Exception {
        var annotations = clazz.getAnnotations();
        var aspects = new ArrayList<IAspect>();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Aspect aspect) {
                var type = (IAspect) aspect.type().getConstructor().newInstance();
                aspects.add(type);
            }
        }

        var instance = clazz.getConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        for (IAspect aspect : aspects) {
                            aspect.before();
                        }
                        var result = method.invoke(instance);
                        for (IAspect aspect : aspects) {
                            aspect.after();
                        }
                        return result;
                    }
                }
        );
    }

    @Test
    public void test() throws Exception {
        IOrder order = ObjectFactory.newInstance(Order.class);
        order.pay();
        order.show();
    }
}
