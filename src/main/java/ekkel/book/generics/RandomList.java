package ekkel.book.generics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by cresh on 26.08.16.
 */
public class RandomList<T> {
    private ArrayList<T> storage = new ArrayList<>();
    private Random random = new Random(74);

    public void add(T item) {
        storage.add(item);
    }

    public T select() {
        return storage.get(random.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> list = new RandomList<>();
        for (String s : "The quick brown fox jumped over the lazy brown dog".split(" ")) list.add(s);
        for (int i = 0; i < 11; i++) {
            System.out.print(list.select() + " ");

        }
    }
}
