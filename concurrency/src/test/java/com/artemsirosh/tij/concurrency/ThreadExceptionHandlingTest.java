package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created at 08-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ThreadExceptionHandlingTest {

    @Test
    void unhandledException() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ExceptionTask());
    }

    @Test
    void handledException() {
        ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionTask());
    }
}
