package com.artemsirosh.mfb.charper11.jointhrds;

/**
 * Created by cresh on 22.03.16.
 */
public class JoinThreads {
    public static void main(String[] args) {
        String threadName;
        System.out.println("Запуск основного потока.");

        MyThread mtZero = new MyThread("Zero");
        MyThread mtOne = new MyThread("First");
        MyThread mtTwo = new MyThread("Second");


        try {
            mtZero.thread.join();
            System.out.println("Zero - завершен.");
            mtOne.thread.join();
            System.out.println("First - завершен.");
            mtTwo.thread.join();
            System.out.println("Second - завершен.");
        } catch (InterruptedException exc) {
            System.out.println("Прерывание основного потока.");
        }
        System.out.println("Завершение основного потока.");
    }
}
