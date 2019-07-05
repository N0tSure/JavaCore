package com.artemsirosh.tij.concurrency;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created at 05-07-2019
 *
 * Command line args: [size] [ponderFactor] [timeout]
 *  size           amount of threads, positive number
 *  ponderFactor   pause time factor, positive number
 *  timeout        flag for task exiting due time out
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class DinningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        final Parameters parameters = parseParameters(args);
        final ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = Stream.generate(Chopstick::new)
                .limit(parameters.getSize())
                .toArray(Chopstick[]::new);

        final LocalTime startTime = LocalTime.now();
        System.out.println("Started at " + startTime);
        for (int i = 0; i < parameters.getSize(); i++) {
            executorService.execute(new Philosopher(
                    chopsticks[i],
                    chopsticks[(i + 1) % parameters.getSize()],
                    i,
                    parameters.getPonderFactor()
            ));
        }

        if (parameters.isTimeout())
            TimeUnit.SECONDS.sleep(5);
        else
            listenSocketAndWait();

        executorService.shutdownNow();

        final LocalTime endTime = LocalTime.now();
        System.out.println("Ended at " + endTime);
        System.out.println("Time elapsed: " + Duration.between(startTime, endTime));
    }

    static void listenSocketAndWait() {
        try (
                final ServerSocket serverSocket = new ServerSocket(8080);
                final Socket clientSocket = serverSocket.accept();
                final PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Stopping execution.");
            out.flush();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    static Parameters parseParameters(final String[] commandLineArgs) {
        final Parameters result;
        if (commandLineArgs.length > 2) {
            result = new Parameters(
                    new Integer(commandLineArgs[0]),
                    new Integer(commandLineArgs[1]),
                    "timeout".equals(commandLineArgs[2])
            );
        } else if (commandLineArgs.length == 2) {
            result = new Parameters(
                    new Integer(commandLineArgs[0]),
                    new Integer(commandLineArgs[1]),
                    false
            );
        } else if (commandLineArgs.length == 1) {
            result = new Parameters(new Integer((commandLineArgs[0])), 5, false);
        } else {
            result = new Parameters(5, 5, false);
        }

        return result;
    }

    static final class Parameters {

        private final int size;
        private final int ponderFactor;
        private final boolean timeout;


        Parameters(int size, int ponderFactor, boolean timeout) {
            this.size = size;
            this.ponderFactor = ponderFactor;
            this.timeout = timeout;
        }

        int getSize() {
            return size;
        }

        int getPonderFactor() {
            return ponderFactor;
        }

        boolean isTimeout() {
            return timeout;
        }
    }
}
