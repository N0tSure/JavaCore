package com.artemsirosh.tij.concurrency;

/**
 * Created at 17-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Fat {

    private volatile double value;
    private final int id;

    public Fat(int id) {
        this.id = id;

        double d = 0;
        for(int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double) i;
        }

        this.value = d;
    }

    public void operation() {
        System.out.println(this + ": " + value);
    }

    @Override
    public String toString() {
        return "Fat #" + id;
    }
}
