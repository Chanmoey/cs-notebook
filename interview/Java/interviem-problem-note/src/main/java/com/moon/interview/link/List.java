package com.moon.interview.link;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;

/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public class List<T> {

    static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size = 0;

    public int size() {
        return size;
    }

    // O(1)
    public void insert(T data) {
        var node = new Node<>(data);
        node.next = head;
        head = node;
        size++;
    }

    public Node<T> find(Predicate<T> predicate) {
        Node<T> cur = head;
        while (cur != null) {
            if (predicate.test(cur.data)) {
                return cur;
            }
            cur = cur.next;
        }

        return null;
    }

    public void remove(Node<T> node) {
        if (head == null) {
            return;
        }

        if (head == node){
            head = head.next;
            size--;
            return;
        }

        Node<T> delete = head.next;
        Node<T> pre = head;
        while (delete != null && delete.data != node.data) {
            delete = delete.next;
            pre = pre.next;
        }

        if (delete != null) {
            pre.next = delete.next;
            size--;
        }
    }

    public void reverse() {
        Node<T> pre = null;
        Node<T> cur = head;
        Node<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        head = pre;
    }

    private Node<T> _reverse2(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        var rest = _reverse2(head.next);

        head.next.next = head;
        head.next = null;
        return rest;
    }

    public void reverse2() {
        head = _reverse2(head);
    }

    public boolean hasLoop1() {
        var set = new HashSet<Node<T>>();
        var p = head;
        while (p != null) {
            if (set.contains(p)) {
                return true;
            }
            set.add(p);
            p = p.next;
        }

        return false;
    }

    public boolean hasLoop() {
        if (head == null || head.next == null) {
            return false;
        }

        var slow = head;
        var fast = head.next.next;

        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    @Test
    public void testInsert() {
        var list = new List<Integer>();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        var p = list.head;
        for (Integer i = 3; p != null; i--) {
            assertEquals(i, p.data);
            p = p.next;
        }
    }

    @Test
    public void testFind() {
        var list = new List<String>();
        list.insert("Java");
        list.insert("Python");
        list.insert("Golang");
        list.insert("C");
        list.insert("JavaScript");
        list.insert("C++");

        var node = list.find(e -> e.equals("Java"));
        assertEquals("Java", node.data);

        node = list.find(e -> e.equals("Python"));
        assertEquals("Python", node.data);

        list.remove(node);
        assertEquals(5, list.size());
        assertEquals(null, list.find(x -> x.equals("Python")));
    }

    @Test
    public void testReverse() {
        var list = new List<Integer>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.reverse();

        var p = list.head;
        for (Integer i = 1; p != null; i++) {
            assertEquals(i, p.data);
            p = p.next;
        }
    }

    @Test
    public void testReverse2() {
        var list = new List<Integer>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.reverse2();

        var p = list.head;
        for (Integer i = 1; p != null; i++) {
            assertEquals(i, p.data);
            p = p.next;
        }
    }

    @Test
    public void testLoop() {
        var list = new List<Integer>();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(0);

        var node = list.find(e -> e == 1);
        node.next = list.head;

        assertEquals(true, list.hasLoop1());
        assertEquals(true, list.hasLoop());
    }
}
