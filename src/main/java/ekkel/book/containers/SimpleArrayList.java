package ekkel.book.containers;

import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by cresh on 20.03.17.
 */
public class SimpleArrayList<T> extends AbstractList<T> implements Iterable<T> {

    private static final int DEF_SIZE = 10;

    private T[] container;
    private Class<T> type;
    private int index;

    @SuppressWarnings("unchecked")
    public SimpleArrayList(Class<T> type, int size) {
        this.container = (T[]) Array.newInstance(type, size);
        this.type = type;
        this.index = 0;
    }

    public SimpleArrayList(Class<T> type) {
        this(type, DEF_SIZE);
    }

    public SimpleArrayList(Class<T> type, T[] container) {
        this.container = container;
        this.type = type;
        this.index = 0;
    }

    public SimpleArrayList(Class<T> type, Collection<T> collection) {
        this(type, collection.size());
        container = collection.toArray(container);
    }

    public boolean add(T t) {
        if (index > container.length)
            extend();

        container[index++] = t;
        return true;
    }

    @Override
    public T get(int index) {
        this.index--;
        return container[index % container.length];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        container = (T[]) Array.newInstance(type, DEF_SIZE);
        index = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int iteratorIndex = 0;
            @Override
            public boolean hasNext() {
                return iteratorIndex < SimpleArrayList.this.index;
            }

            @Override
            public T next() {
                return SimpleArrayList.this.container[iteratorIndex++];
            }
        };
    }

    @SuppressWarnings("unchecked")
    private void extend() {
        T[] extended = (T[]) Array.newInstance(type, (container.length + (container.length / 2)));
        System.arraycopy(container, 0, extended, 0, container.length);
        container = extended;
    }
}
