package com.moon.interview.queue;

import com.moon.interview.stack.Stack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年08月05日
 */
public class QueueByStack<T> {
    Stack<T> in = new Stack<>();
    Stack<T> out = new Stack<>();

    public void enqueue(T e) {
        in.push(e);
    }

    public T dequeue() {
        if (out.size() == 0) {
            while (in.size() != 0) {
                out.push(in.pop());
            }
        }

        return out.pop();
    }

    @Test
    public void test() {
        var queue = new QueueByStack<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
    }
}
