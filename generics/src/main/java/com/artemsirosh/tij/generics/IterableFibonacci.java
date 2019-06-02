package com.artemsirosh.tij.generics;

import java.util.Iterator;

/**
 * Created by cresh on 26.08.16.
 */
public class IterableFibonacci implements Iterable<Integer> {
    private int count;
    private Fibonacci fibonacci;

    public IterableFibonacci(int count) {
        this.fibonacci = new Fibonacci();
        this.count = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return count > 0;
            }

            @Override
            public Integer next() {
                count--;
                return fibonacci.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (Integer integer : new IterableFibonacci(18)) System.out.print(integer + " ");
    }
}
