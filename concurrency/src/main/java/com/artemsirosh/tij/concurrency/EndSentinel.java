package com.artemsirosh.tij.concurrency;

/**
 * Created at 15-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class EndSentinel extends DelayedTask {

    private final FinisherTask<?> finisher;

    public EndSentinel(int id, int deltaInMills, FinisherTask<?> finisher) {
        super(id, deltaInMills);
        this.finisher = finisher;
    }

    @Override
    public void run() {
        finisher.setResult(null);
    }
}
