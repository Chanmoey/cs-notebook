package com.moon.interview.reflection;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class ProxyExampleTest {

    @Test
    public void testProxy() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        IOrder order = Aspect.getProxy(Order.class, "com.moon.interview.reflection.TimeUsageAspect");
        order.pay();
        order.show();
    }
}
