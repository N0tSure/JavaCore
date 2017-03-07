package ekkel.book.generics.latency;

/**
 * Created by cresh on 20.12.16.
 */
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T t) {
        super.add(t);
    }
}
