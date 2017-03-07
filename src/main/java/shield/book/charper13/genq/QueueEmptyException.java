package shield.book.charper13.genq;

/**
 * Created by cresh on 15.04.16.
 */
class QueueEmptyException extends Exception {
    @Override
    public String toString() {
        return "\nОчередь пуста.\n";
    }
}
