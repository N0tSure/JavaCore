package shield.book.charper13.genq;

/**
 * Created by cresh on 15.04.16.
 */
public interface IGenQ<T> {
    void put(T t) throws QueueFullException;
    T get() throws QueueEmptyException;
}
