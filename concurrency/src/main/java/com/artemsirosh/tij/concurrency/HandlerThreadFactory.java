package com.artemsirosh.tij.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler((t, exc) -> System.out.println("Caught " + exc));
        return thread;
    }
}
