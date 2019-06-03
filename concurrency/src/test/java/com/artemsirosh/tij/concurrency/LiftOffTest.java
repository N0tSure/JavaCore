package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * Created at 03-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class LiftOffTest {

    @Test
    @DisplayName("Task work in main thread")
    void singleThread() {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }

    @Test
    @DisplayName("Task is works in separated thread")
    void separatedThread() {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("Main thread end.");
    }

    @Test
    @DisplayName("Several traceable task running in treads")
    void severalTasks() {
        Stream.generate(TraceableTask::new)
                .limit(5)
                .map(Thread::new)
                .forEach(Thread::start);
    }

}
