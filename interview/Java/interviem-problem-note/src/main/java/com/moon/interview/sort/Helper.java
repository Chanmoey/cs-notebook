package com.moon.interview.sort;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class Helper {
    public static void swap(int[] a, int l, int r) {
        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }
}
