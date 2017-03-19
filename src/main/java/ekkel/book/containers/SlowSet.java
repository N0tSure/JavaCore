package ekkel.book.containers;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cresh on 18.03.17.
 */
class SlowSet<T> extends AbstractSet<T> {
    private List<T> values;

    public SlowSet() {
        this.values = new ArrayList<>();
    }

    @Override
    public boolean add(T t) {
        if (values.contains(t))
            return false;

        return values.add(t);
    }

    @Override
    public boolean contains(Object o) {
        return values.contains(o);
    }

    @Override
    public void clear() {
        values.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return values.iterator();
    }

    @Override
    public int size() {
        return values.size();
    }
}
