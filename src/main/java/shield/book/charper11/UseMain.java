package shield.book.charper11;

/**
 * Created by cresh on 24.03.16.
 */
public class UseMain {
    static void printTread(Thread thread) {
        System.out.printf("Имя потока: %s\n",thread.getName());
        System.out.printf("Приоритет потока: %d\n\n",thread.getPriority());
    }

    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        printTread(thread);

        thread.setName("Основной поток #1");
        thread.setPriority(Thread.MAX_PRIORITY);

        printTread(thread);

        try {
            thread.join();
        } catch (InterruptedException exc) {
            System.out.println();
        }
    }
}
