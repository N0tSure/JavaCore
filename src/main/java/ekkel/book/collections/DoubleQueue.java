package ekkel.book.collections;

import java.util.*;

/**
 * Created by cresh on 15.03.16.
 */
public class DoubleQueue {
    public static void main(String[] args) {
        Random rnd = new Random(47);

        List<Double> ld = Arrays.asList(rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),
                rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),
                rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble(),rnd.nextDouble());
        PriorityQueue<Double> pq = new PriorityQueue<Double>(ld);

        while (pq.peek() != null) System.out.println(pq.poll());
    }
}
