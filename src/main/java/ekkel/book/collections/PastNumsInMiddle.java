package ekkel.book.collections;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by NotSure on 03.03.16.
 */
public class PastNumsInMiddle {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        ListIterator<Integer> iterator = list.listIterator();
        Random rand = new Random(47);
        iterator.add(rand.nextInt());
        iterator.add(rand.nextInt());
        System.out.println(list);
        for (int i = 0; i < 10; i++) {
            iterator.previous();
            iterator.add(rand.nextInt());
        }
        System.out.println(list);

    }
}
