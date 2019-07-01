package com.artemsirosh.tij.concurrency.toastomatic;

/**
 * Created at 01-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class Toast {

    private final int id;
    private Status status;

    Toast(int id) {
        this.id = id;
        this.status = Status.DRY;
    }

    void butter() {
        status = Status.BUTTERED;
    }

    public int getId() {
        return id;
    }

    void jelly() {
        status = Status.JELLIED;
    }

    void jam() {
        status = Status.JAMMED;
    }

    Status getStatus() {
        return status;
    }

    void peanutButter() {
        status = Status.PEANUT_BUTTERED;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + id + ": " + status;
    }

    public enum Status {
        DRY, BUTTERED, PEANUT_BUTTERED, JAMMED, JELLIED
    }
}
