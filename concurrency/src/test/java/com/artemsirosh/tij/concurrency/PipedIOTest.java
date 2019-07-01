package com.artemsirosh.tij.concurrency;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.*;

/**
 * Created at 02-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class PipedIOTest {

    private static void receiverTask(PipedReader reader) {
        try {
            while (true) {
                System.out.println("Read: " + (char) reader.read());
            }
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

    private static void queueReceiverTask(BlockingQueue<Character> queue) {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Read: " + queue.take());
            }
        } catch (InterruptedException exc) {
            System.out.println("Receiver interrupted.");
        }
    }

    private static void queueSenderTask(BlockingQueue<Character> queue) {
        try {
            while (!Thread.interrupted()) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    queue.put(c);
                    TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500));
                }
            }
        } catch (InterruptedException exc) {
            System.out.println("Sender interrupted.");
        }
    }

    private static void senderTask(PipedWriter writer) {
        try {
            while (true) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    writer.write(c);
                    TimeUnit.MILLISECONDS.sleep(Math.round(Math.random() * 500));
                }
            }
        } catch (IOException exc) {
            System.err.println(exc);
        } catch (InterruptedException exc) {
            System.out.println("Sender interrupted.");
        }
    }

    @Test
    void queuePipelineDemo() throws InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final BlockingQueue<Character> queue = new LinkedBlockingQueue<>();

        executor.execute(() -> queueSenderTask(queue));
        executor.execute(() -> queueReceiverTask(queue));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }

    @Test
    void pipedIODemo() throws IOException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final PipedWriter writer = new PipedWriter();
        final PipedReader reader = new PipedReader(writer);

        executor.execute(() -> senderTask(writer));
        executor.execute(() -> receiverTask(reader));

        TimeUnit.SECONDS.sleep(5);
        executor.shutdownNow();
    }
}
