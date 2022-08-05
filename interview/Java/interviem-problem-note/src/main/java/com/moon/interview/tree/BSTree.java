package com.moon.interview.tree;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @author Chanmoey
 * @date 2022年08月04日
 */
public class BSTree<T extends Comparable<T>> {

    private BSTNode<T> root = null;

    static class BSTNode<T> {

        BSTNode<T> left = null;

        BSTNode<T> right = null;

        T data;

        public BSTNode(T data) {
            this.data = data;
        }
    }

    public void add(T element) {
        BSTNode<T> node = new BSTNode<>(element);
        if (root == null) {
            root = node;
            return;
        }

        add(root, node);
    }

    private void add(BSTNode<T> root, BSTNode<T> element) {

        if (root.data.compareTo(element.data) >= 0) {
            if (root.left == null) {
                root.left = element;
                return;
            }
            add(root.left, element);
        } else {
            if (root.right == null) {
                root.right = element;
                return;
            }
            add(root.right, element);
        }
    }

    public static <T> void preOrder(BSTNode<T> root) {

        if (root == null) {
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static <T> void postOrder(BSTNode<T> root) {
        if (root == null) {
            return;
        }

        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.data);

    }

    public static <T> void inOrder(BSTNode<T> root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.data);
        inOrder(root.right);
    }

    public static <T> void bfs(BSTNode<T> node) {
        var queue = new ArrayDeque<BSTNode<T>>();
        queue.addLast(node);

        while (!queue.isEmpty()) {
            var item = queue.removeFirst();
            System.out.println(item.data);

            if (item.left != null) {
                queue.addLast(item.left);
            }
            if (item.right != null) {
                queue.addLast(item.right);
            }
        }
    }

    public static <T> void reverse(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        BSTNode<T> temp = node.left;
        node.left = node.right;
        node.right = temp;

        reverse(node.left);
        reverse(node.right);
    }

    @Test
    public void testAdd() {
        List<Integer> list = List.of(10, 7, 5, 8, 15, 30, 21);

        var tree = new BSTree<Integer>();
        for (Integer i : list) {
            tree.add(i);
        }

        TreePrinter printer = new TreePrinter();
        printer.print(tree.root);

        BSTree.preOrder(tree.root);
        System.out.println();
        BSTree.inOrder(tree.root);
        System.out.println();
        BSTree.postOrder(tree.root);
    }

    @Test
    public void testReverse() {
        List<Integer> list = List.of(10, 7, 5, 8, 15, 30, 21);

        var tree = new BSTree<Integer>();
        for (Integer i : list) {
            tree.add(i);
        }

        TreePrinter printer = new TreePrinter();
        printer.print(tree.root);

        reverse(tree.root);

        printer.print(tree.root);
    }

    @Test
    public void testBfs() {
        List<Integer> list = List.of(10, 7, 5, 8, 15, 30, 21);

        var tree = new BSTree<Integer>();
        for (Integer i : list) {
            tree.add(i);
        }

        TreePrinter printer = new TreePrinter();
        printer.print(tree.root);

        BSTree.bfs(tree.root);
    }
}
