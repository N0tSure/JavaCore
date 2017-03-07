package shield.book.charper11.morethrds;

/**
 * Created by NotSure on 21.03.16.
 */
public class MoreThreads {
    public static void main(String[] args) {
        System.out.println("Запуск основного потока.");

        MyThread mtOne = new MyThread("Child #1");
        MyThread mtTwo = new MyThread("Child #2");
        MyThread mtThree = new MyThread("Child #3");

        for (int i = 0; i < 50; i++) {
            System.out.print(".");

            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) {
                System.out.println("Основной поток прерван.");
            }
        }
        while (mtOne.thread.isAlive() || mtTwo.thread.isAlive() || mtThree.thread.isAlive());
        System.out.println("Завершение основного потока.");
    }
}
