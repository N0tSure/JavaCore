package com.artemsirosh.mfb.charper11.watch;

/**
 * Created by cresh on 24.03.16.
 */
class MyThread implements Runnable {
    Thread thread;
    TickTock tickTock;

    MyThread(String name,TickTock tickTock) {
        thread = new Thread(this,name);
        this.tickTock=tickTock;
        thread.start();
    }

    @Override
    public void run() {
        if (thread.getName().equals("Tick")) {
            for (int i = 0; i < 5; i++) tickTock.tick(true);
            tickTock.tick(false);
        } else {
            for (int i = 0; i < 5; i++) tickTock.tock(true);
            tickTock.tock(false);
        }
    }
}
