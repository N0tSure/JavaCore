package shield.book.charper11.suspndd;

/**
 * Created by cresh on 24.03.16.
 */
public class Suspend {
    public static void main(String[] args) {
        MyThread thread = new MyThread("My Thread");

        try {
            Thread.sleep(1000);

            thread.suspend();
            System.out.println("Suspending thread");
            Thread.sleep(1000);

            thread.resume();
            System.out.println("Resume to thread");
            Thread.sleep(1000);

            thread.suspend();
            System.out.println("Suspending thread");
            Thread.sleep(1000);

            thread.resume();
            System.out.println("Resume to thread");
            Thread.sleep(1000);

            thread.stop();
            System.out.println("Thread stopped.");
        } catch (InterruptedException exc) {
            System.out.println("Main thread was interrupted.");
        }

        try {
            thread.thread.join();
        } catch (InterruptedException exc) {
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Complete.");
    }
}
