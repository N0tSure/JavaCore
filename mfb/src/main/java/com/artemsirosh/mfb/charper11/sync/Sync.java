package com.artemsirosh.mfb.charper11.sync;

/**
 * Created by cresh on 24.03.16.
 */
class Sync {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,8,9};

        MyThread first = new MyThread("First",nums);
        MyThread scnd = new MyThread("Second",nums);
        MyThread third = new MyThread("Last",nums);

        try {
            first.thread.join();
            scnd.thread.join();
            third.thread.join();
        } catch (InterruptedException exc) {
            System.out.println("Main thread interrupted.");
        }
    }
}
