package com.moon.interview.annotation;

/**
 * @author Chanmoey
 * @date 2022年08月02日
 */
public class TimeUsageAspect implements IAspect {

    private long start;

    @Override
    public void before() {
        start = System.currentTimeMillis();
    }

    @Override
    public void after() {
        var usage = System.currentTimeMillis() - start;
        System.out.println("time usage: " + usage + "ms");
    }
}
