package com.artemsirosh.tij.concurrency;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class EvenCheckerTask implements Runnable {

    private final IntGenerator generator;
    private final int id;

    EvenCheckerTask(int id, IntGenerator generator) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int value = generator.next();
            if ((value & 1) != 0) {
                System.out.println(this + ": " + value + " not even!");
                generator.cancel();
            }
        }
    }

    @Override
    public String toString() {
        return "EvenChecker #" + id;
    }
}
