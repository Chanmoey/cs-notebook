package com.moon.interview.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class BucketSort implements IImutableSorter{

    public List<Integer> sort(List<Integer> a) {
        int k = 100;
        var buckets = new ArrayList<LinkedList<Integer>>(100);
        var list = new ArrayList<Integer>();

        for (int i = 0; i < k; i++) {
            buckets.add(new LinkedList<>());
        }

        for (var item : a) {
            buckets.get(item % k).add(item);
        }

        for (var bucket : buckets) {
            list.addAll(bucket);
        }

        return list;
    }
}
