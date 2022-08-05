package com.moon.interview.stack;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年08月05日
 */
public class Stack<T> {

    LinkedList<T> list = new LinkedList<>();

    public void push(T e) {
        list.addFirst(e);
    }

    public T pop() {
        return list.removeFirst();
    }

    public int size() {
        return list.size();
    }

    @Test
    public void test() {
        var stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }
}
