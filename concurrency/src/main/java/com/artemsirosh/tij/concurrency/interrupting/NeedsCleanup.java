package com.artemsirosh.tij.concurrency.interrupting;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class NeedsCleanup {

    private final int id;

    NeedsCleanup(int id) {
        this.id = id;
        System.out.println(this + ": created.");
    }

    void cleanup() {
        System.out.println(this + ": clean up.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + id;
    }
}
