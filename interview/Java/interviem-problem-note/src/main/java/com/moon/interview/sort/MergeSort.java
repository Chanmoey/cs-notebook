package com.moon.interview.sort;

import java.util.Arrays;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class MergeSort implements IMutableSorter{
    @Override
    public void sort(int[] a) {
        mergeSort(a, 0, a.length);
    }

    // [l, r)
    private void mergeSort(int[] a, int l, int r) {
        if (r - l <= 1) {
            return;
        }

        int mid = l + (r - l) / 2;
        mergeSort(a, l, mid);
        mergeSort(a, mid, r);
        merge(a, l, mid, r);
    }

    private void merge(int[] a, int l, int mid, int r) {
        int[] b = Arrays.copyOfRange(a, l, mid + 1);
        int[] c = Arrays.copyOfRange(a, mid, r + 1);

        // sentinel
        b[b.length - 1] = c[c.length - 1] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        for (int k = l; k < r; k++) {
            if (b[i] < c[j]) {
                a[k] = b[i++];
            } else {
                a[k] = c[j++];
            }
        }
    }
}
