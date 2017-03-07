package shield.book.charper13.genq;

/**
 * Created by cresh on 15.04.16.
 */
class GenQueue<T> implements IGenQ<T> {
    private T[] ts;
    private int putCounter,getCounter;

    public GenQueue(T[] ts) {
        this.ts=ts;
        putCounter=0;
        getCounter=0;
    }

    @Override
    public void put(T t) throws QueueFullException {
        if (putCounter==ts.length-1) throw new QueueFullException(ts.length);
        ts[putCounter++] = t;
    }

    @Override
    public T get() throws QueueEmptyException {
        if (getCounter==putCounter) throw new QueueEmptyException();
        return ts[getCounter++];
    }
}
