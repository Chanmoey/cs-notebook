package com.moon.interview.annotation;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public interface IOrder {

    void pay() throws InterruptedException;

    void show();
}
