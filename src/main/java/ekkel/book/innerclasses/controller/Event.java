package ekkel.book.innerclasses.controller;

/**
 * Created by cresh on 03.07.16.
 */
abstract class Event {
    private long eventTime;
    protected final long delayTime;
    public Event(long delayTime) {
        this.delayTime = delayTime;
        this.start();
    }

    public void start() {
        this.eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready() {
        return System.nanoTime() >= this.eventTime;
    }

    public abstract void action();
}
