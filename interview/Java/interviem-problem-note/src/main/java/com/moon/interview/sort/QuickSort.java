package com.moon.interview.sort;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class QuickSort implements IImutableSorter {
    @Override
    public List<Integer> sort(List<Integer> a) {
        return this.quickSort(a);
    }

    private List<Integer> quickSort(List<Integer> a) {

        if (a.size() <= 1) {
            return a;
        }

        // | ---left--- | x | ---right--- |
        var x = a.get(0);
        var left = a.stream().filter(i -> i < x).collect(toList());
        var mid = a.stream().filter(i -> i.equals(x)).toList();
        var right = a.stream().filter(i -> i > x).toList();

        left = quickSort(left);
        right = quickSort(right);
        left.addAll(mid);
        left.addAll(right);
        return left;
    }
}
