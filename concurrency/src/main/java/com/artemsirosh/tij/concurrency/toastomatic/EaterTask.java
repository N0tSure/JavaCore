package com.artemsirosh.tij.concurrency.toastomatic;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Created at 02-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class EaterTask implements Callable<List<Toast>> {

    private final BlockingQueue<Toast> toastQueue;
    private final List<Toast> result;

    EaterTask(BlockingQueue<Toast> toastQueue) {
        this.toastQueue = toastQueue;
        this.result = new ArrayList<>();
    }

    @Override
    public List<Toast> call() {
        try {
            while (!Thread.interrupted()) {
                final Toast toast = toastQueue.take();
                if (!isToastFinished(toast))
                    result.add(toast);

                System.out.println("Chomp " + toast);
            }
        } catch (InterruptedException exc) {
            System.out.println("Eater: interrupted.");
        }

        return result;
    }

    private boolean isToastFinished(Toast toast) {
        return toast.getStatus() == Toast.Status.JAMMED || toast.getStatus() == Toast.Status.JELLIED;
    }

}
