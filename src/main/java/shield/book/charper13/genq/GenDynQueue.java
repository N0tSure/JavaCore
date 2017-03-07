package shield.book.charper13.genq;

/**
 * Created by cresh on 20.04.16.
 */
class GenDynQueue<T> implements IGenQ<T> {
    private Object[] ts = new Object[10];
    private int putCounter, getCounter;

    public GenDynQueue() {
        putCounter=0;
        getCounter=0;
    }

    public GenDynQueue(int initialCapacity) {
        this.ts = new Object[initialCapacity];
        putCounter=0;
        getCounter=0;
    }

    public GenDynQueue(T[] ts) {
        this.ts = ts;
        this.putCounter = 0;
        this.getCounter = 0;
    }

    @Override
    public void put(T t) throws QueueFullException {
        if (putCounter==ts.length-1) {
            Object[] tmp = new Object[(ts.length*3)/2];
            System.arraycopy(ts,0,tmp,0,ts.length);
            ts=tmp;
        }
        putCounter++;
        ts[putCounter] = t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get() throws QueueEmptyException {
        if (putCounter==getCounter) throw new QueueEmptyException();
        getCounter++;
        return (T) ts[getCounter];
    }
}
