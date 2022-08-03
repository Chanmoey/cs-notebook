package com.moon.interview.sort;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class InsertionSort implements IMutableSorter {
    @Override
    public void sort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int c = a[i];
            int j = i;

            for (; j > 0 && a[j - 1] > c; j--) {
                a[j] = a[j - 1];
            }
            a[j] = c;
        }
    }
}
