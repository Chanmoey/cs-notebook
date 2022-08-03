package com.moon.interview.sort;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Chanmoey
 * @date 2022年08月03日
 */
public class SortTests {

    private static final Random random = new Random();

    @Test
    public void testInsertionSort() {
        sortTest(InsertionSort.class, 100000);
    }

    @Test
    public void testSelectionSort() {
        sortTest(SelectionSort.class, 100000);
    }

    @Test
    public void testBubbleSort() {
        sortTest(BubbleSort.class, 100000);
    }

    @Test
    public void testMergeSort() {
        sortTest(MergeSort.class, 1000000);
    }

    @Test
    public void testQuickSort() {
        sortTest(QuickSort.class, 1000000);
    }

    @Test
    public void testQuickSort1() {
        sortTest(QuickSort1.class, 1000000);
    }

    @Test
    public void testBucketSort() {
        BucketSort bucketSort = new BucketSort();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(random.nextInt(100));
        }

        var start = System.currentTimeMillis();
        list = bucketSort.sort(list);
        System.out.println("time usage: " + (System.currentTimeMillis() - start));
        assertSorted(list);
    }

    static List<Integer> gen(int n) {
        var a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(random.nextInt(n));
        }
        return a;
    }

    public void sortTest(Class<?> cls, int n) {
        try {
            var constructor = cls.getConstructor();
            var instance = constructor.newInstance();

            var start = System.currentTimeMillis();
            if (instance instanceof IImutableSorter iImutableSorter) {
                var a = gen(n);
                a = iImutableSorter.sort(a);

                System.out.println("time usage: " + (System.currentTimeMillis() - start) + "ms");
                assertSorted(a);
            } else if (instance instanceof IMutableSorter iMutableSorter) {
                var a = gen(n).stream().mapToInt(x -> x).toArray();
                iMutableSorter.sort(a);

                System.out.println("time usage: " + (System.currentTimeMillis() - start) + "ms");
                assertSorted(a);
            }

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void assertSorted(int[] a) {
        assertSorted(Arrays.stream(a).boxed().toList());
    }

    static void assertSorted(List<Integer> a) {
        int o = Integer.MIN_VALUE;
        for (var i : a) {
            if (o > i) {
                System.out.println(a);
                Assert.fail("Array not in sorted order");
            }
            o = i;
        }
    }
}
