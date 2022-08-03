package com.moon.interview.annotation;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
@Aspect(type = TimeUsageAspect.class)
public class Order implements IOrder {

    private int state = 0;

    @Override
    public void pay() throws InterruptedException {
        Thread.sleep(50);
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.println("order status: " + state);
    }
}
