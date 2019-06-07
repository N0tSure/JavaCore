package com.artemsirosh.tij.concurrency;

import java.util.concurrent.Callable;

/**
 * Created at 07-06-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
class TaskWithResult implements Callable<String> {

    private final int id;

    TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return getClass().getSimpleName() + " #" + id;
    }
}
