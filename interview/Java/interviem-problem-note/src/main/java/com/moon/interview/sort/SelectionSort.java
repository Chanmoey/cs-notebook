package com.moon.interview.sort;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class SelectionSort implements IMutableSorter{
    @Override
    public void sort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            int j = maxIndex(a, 0, i + 1);
            Helper.swap(a, i, j);
        }
    }

    // [l, r)
    private static int maxIndex(int[] a, int l, int r) {
        int max = a[l];

        int maxIndex = l;
        for (int k = l; k < r; k++) {
            if (max < a[k]) {
                max = a[k];
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
