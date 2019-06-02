package com.artemsirosh.mfb.charper11.watch;

/**
 * Created by cresh on 24.03.16.
 */
public class ThreadCom {
    public static void main(String[] args) {
        TickTock tickTock = new TickTock();
        MyThread first = new MyThread("Tick",tickTock);
        MyThread scnd = new MyThread("Tock",tickTock);

        try {
            first.thread.join();
            scnd.thread.join();
        } catch (InterruptedException exc) {
            System.out.println("Main thread interruption.");
        }
    }
}
