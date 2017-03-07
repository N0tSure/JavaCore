package shield.book.charper11.suspndd;

/**
 * Created by cresh on 24.03.16.
 */
class MyThread implements Runnable {
    Thread thread;
    private volatile boolean suspended;
    private volatile boolean stopped;

    MyThread(String name) {
        thread = new Thread(this,name);
        suspended=false;
        stopped=false;
        thread.start();
    }

    @Override
    public void run() {
        System.out.printf("%s - started.\n",thread.getName());
        try {
            for (int i = 0; i < 100000; i++) {
                System.out.print(i+" ");
                if ((i%10)==0) {
                    System.out.println();
                    Thread.sleep(50);
                }
                synchronized (this) {
                    while (suspended) {
                        wait();
                    }
                    if (stopped) break;
                }
            }
        } catch (InterruptedException exc) {
            System.out.printf("%s interrupted.\n",thread.getName());
        }
        System.out.printf("\n%s complete.\n",thread.getName());
    }

    synchronized void stop() {
        stopped=true;
        suspended=false;
        notify();
    }

    synchronized void suspend() {
        suspended=true;
    }

    synchronized void resume() {
        suspended=false;
        notify();
    }
}
