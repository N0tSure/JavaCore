package shield.book.charper11.jointhrds;

/**
 * Created by cresh on 22.03.16.
 */
class MyThread implements Runnable {
    Thread thread;

    public MyThread(String name) {
        thread = new Thread(this,name);
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf("%s - запуск.\n",thread.getName());
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(400);
                System.out.printf("В %1$s счетчик: %2$d\n", thread.getName(), i);
            }
        } catch (InterruptedException exc) {
            System.out.printf("%s - прерван.\n",thread.getName());
        }
        System.out.printf("%s - завершен.\n",thread.getName());
    }
}
