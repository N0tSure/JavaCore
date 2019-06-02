package com.artemsirosh.tij.collections;

import java.util.*;
/**
 * Created by cresh on 15.03.16.
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Random rnd = new Random(47);

        for (int i = 0; i < 10; i++) pq.offer(rnd.nextInt(10+i));
        QueueDemo.printQ(pq);

        List<Integer> ints = Arrays.asList(56,98,65,665,998,62,696,566,22,69,8,3);
        pq = new PriorityQueue<Integer>(ints);
        QueueDemo.printQ(pq);

        pq = new PriorityQueue<Integer>(ints.size(),Collections.reverseOrder());
        pq.addAll(ints);
        QueueDemo.printQ(pq);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPriorityQueue = new PriorityQueue<String>(strings);
        QueueDemo.printQ(stringPriorityQueue);

        stringPriorityQueue = new PriorityQueue<String>(strings.size(),Collections.reverseOrder());
        stringPriorityQueue.addAll(strings);
        QueueDemo.printQ(stringPriorityQueue);

        Set<Character> characters = new HashSet<Character>();
        for (char c : fact.toCharArray()) characters.add(c);
        PriorityQueue<Character> characterPriorityQueue = new PriorityQueue<Character>(characters);
        QueueDemo.printQ(characterPriorityQueue);

    }
}
