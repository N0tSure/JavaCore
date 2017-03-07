package shield.book.charper13.genq;

/**
 * Created by cresh on 15.04.16.
 */
class QueueFullException extends Exception {
    private int size;

    QueueFullException(int size) {
      this.size=size;
    }

    @Override
    public String toString() {
        return String.format("\nОчередь заполнена. Максимальный размер очереди: %d\n",size);
    }
}
