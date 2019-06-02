package com.artemsirosh.mfb.charper13.genq;

/**
 * Created by cresh on 20.04.16.
 */
class GenCircQueue<T> implements IGenQ<T> {
    private Object[] ts = new Object[10];
    private int putCounter, getCounter;

    public GenCircQueue(T[] ts) {
        this.ts = ts;
        putCounter=0;
        getCounter=0;
    }

    public GenCircQueue(int size) {
        this.ts = new Object[size];
        putCounter=0;
        getCounter=0;
    }

    public GenCircQueue() {
        putCounter=0;
        getCounter=0;
    }

    @Override
    public void put(T t) throws QueueFullException {
        if (putCounter+1==getCounter | ((putCounter==ts.length-1) & (getCounter==0)))
            throw new QueueFullException(ts.length);
        putCounter++;
        if (putCounter==ts.length) putCounter=0;
        ts[putCounter] = t;
    }

    @Override
    public T get() throws QueueEmptyException {
        if (getCounter==putCounter) throw new QueueEmptyException();
        getCounter++;
        if (getCounter==ts.length) getCounter=0;
        return (T) ts[getCounter];
    }
}
