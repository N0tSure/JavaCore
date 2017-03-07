package shield.book.charper11;

/**
 * Created by NotSure on 21.03.16.
 */
class EThread extends Thread {
    EThread(String name) {
        super(name);
        start();
    }

    @Override
    public void run() {
        System.out.println(getName() + " - запуск");
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(400);
                System.out.println("В " + getName() + " счетчик, " + i);
            }
        } catch (InterruptedException exc) {
            System.out.println(getName() + " - прерван");
        }
        System.out.println(getName() + " - завершен");
    }
}
public class ExtendThread {
    public static void main(String[] args) {
        System.out.println("Запуск основного потока.");
        MyThread myThread = new MyThread("Child #1");
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException exc) {
                System.out.println("Прерывание основного потока.");
            }
        }
        System.out.println("Завершение основного потока.");
    }
}
