package ekkel.book.containers;

import java.util.*;

/**
 * Created by cresh on 18.03.17.
 */
public class SimpleHashSet<T> extends AbstractSet<T> {
    private static final int SIZE = 997;

    private LinkedList<T>[] buckets;

    @SuppressWarnings("unchecked")
    public SimpleHashSet() {
        buckets = new LinkedList[SIZE];
    }

    @Override
    public boolean add(T t) {
        int index = getIndex(t.hashCode());
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();

        LinkedList<T> bucket = buckets[index];
        if (!bucket.contains(t))
            return bucket.add(t);

        return false;
    }

    @Override
    public boolean contains(Object o) {
        if (buckets[getIndex(o.hashCode())] != null)
            return buckets[getIndex(o.hashCode())].contains(o);

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (contains(o)) {
            int index = getIndex(o.hashCode());
            return buckets[index].remove(o);
        }
        return false;
    }

    @Override
    public void clear() {
        for (LinkedList<T> bucket : buckets) {
            if (bucket != null)
                bucket.clear();
        }
    }

    @Override
    public Iterator<T> iterator() {
        List<T> elements = new LinkedList<T>();
        for (LinkedList<T> bucket : buckets) {
            if (bucket != null)
                elements.addAll(bucket);
        }
        return elements.iterator();
    }

    @Override
    public int size() {
        int size = 0;

        for (LinkedList<T> bucket : buckets) {
            if (bucket != null)
                size += bucket.size();
        }
        return size;
    }

    private int getIndex(int hash) {
        return Math.abs(hash) % SIZE;
    }
}
