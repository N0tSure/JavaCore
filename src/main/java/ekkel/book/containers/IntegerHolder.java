package ekkel.book.containers;

import com.google.common.base.MoreObjects;
import ekkel.book.generics.wildcards.Holder;

/**
 * Created by cresh on 12.03.17.
 */
class IntegerHolder extends Holder<Integer> implements Comparable<IntegerHolder> {
    public IntegerHolder() {
        super((int) (Math.random() * 100));
    }

    @Override
    public Integer get() {
        return super.get();
    }

    @Override
    public int compareTo(IntegerHolder o) {
        return Integer.compare(this.get(), o.get());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " #" + Integer.toString(this.get());
    }
}
