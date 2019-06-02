package com.artemsirosh.tij.collections;

import java.util.*;
import java.util.Queue;

/**
 * Created by cresh on 14.03.16.
 */
public class QueueDemo {
    public static void printQ(Queue queue) {
        while (queue.peek() != null) System.out.print(queue.remove() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random rnd = new Random(47);
        for (int i = 0; i < 10; i++) queue.offer(rnd.nextInt(i+10));
        printQ(queue);

        Queue<Character> qc = new LinkedList<Character>();
        for (char c : "Brontosaurus".toCharArray()) qc.offer(c);
        printQ(qc);
    }
}
