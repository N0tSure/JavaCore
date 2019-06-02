package com.artemsirosh.tij.generics;

/**
 * Created by cresh on 26.08.16.
 */
public class LinkedStack<T> {
    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node() {
            this.item = null;
            this.next = null;
        }

        private Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }

        private boolean end() {
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<>();

    public void push(T item) {
        this.top = new Node<T>(item,this.top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) top = top.next;
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        for (String s : "Phasers on stun!".split(" ")) stack.push(s);
        String s;
        while ((s = stack.pop()) != null) System.out.println(s);
    }
}
