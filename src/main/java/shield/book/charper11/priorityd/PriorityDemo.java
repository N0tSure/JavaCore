package shield.book.charper11.priorityd;

/**
 * Created by cresh on 23.03.16.
 */
public class PriorityDemo {
    public static void main(String[] args) {
        Priority high = new Priority("High Priority");
        Priority low = new Priority("Low Priority");

        high.thread.setPriority(Thread.MAX_PRIORITY);
        low.thread.setPriority(Thread.MIN_PRIORITY);

        high.thread.start();
        low.thread.start();

        try {
            high.thread.join();
            low.thread.join();
        } catch (InterruptedException exc) {
            System.out.println("Прерывание основного потока.");
        }
        System.out.printf("Счетчик потока High Priority: %d\n",high.count);
        System.out.printf("Счетчик потока Low Priority: %d\n",low.count);
    }
}
