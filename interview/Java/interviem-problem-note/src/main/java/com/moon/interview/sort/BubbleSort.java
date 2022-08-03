package com.moon.interview.sort;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class BubbleSort implements IMutableSorter {
    @Override
    public void sort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            bubble(a, 0, i + 1);
        }
    }

    // [l, r)
    private void bubble(int[] a, int l, int r) {
        for (int k = l; k < r - 1; k++) {
            if (a[k] > a[k + 1]) {
                Helper.swap(a, k, k + 1);
            }
        }
    }
}
