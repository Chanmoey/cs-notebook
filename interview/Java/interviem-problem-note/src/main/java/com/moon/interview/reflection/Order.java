package com.moon.interview.reflection;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class Order implements IOrder{

    private int state = 0;

    @Override
    public void pay() throws InterruptedException {
        Thread.sleep(500);
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.println("order status: " + state);
    }
}
