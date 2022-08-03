package com.moon.interview.sort;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class QuickSort1 implements IMutableSorter {
    @Override
    public void sort(int[] a) {
        this.quickSort(a, 0, a.length);
    }

    private void quickSort(int[] a, int l, int r) {
        if (r - l <= 1) {
            return;
        }

        int i = partition(a, l, r);

        quickSort(a, l, i);
        quickSort(a, i + 1, r);
    }

    private int partition(int[] a, int l, int r) {
        int x = a[l];

        int i = l + 1;
        int j = r;
        while (i != j) {
            if (a[i] < x) {
                i++;
            } else {
                Helper.swap(a, i, --j);
            }
        }
        Helper.swap(a, i - 1, l);
        return i - 1;
    }
}
