package com.artemsirosh.tij.concurrency.balancer;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created at 29-07-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
public class Monitor implements Runnable {

    private final PriorityQueue<Server> runningServers;
    private final Queue<Server> pooledQueue;
    private final int monitoringInterval;

    private static String serverToInfoString(Server server) {
        return String.format("%s:%d", server.shortDescription(), server.getAliveConnections().size());
    }

    public Monitor(PriorityQueue<Server> runningServers, Queue<Server> pooledQueue, int monitoringInterval) {
        this.runningServers = runningServers;
        this.pooledQueue = pooledQueue;
        this.monitoringInterval = monitoringInterval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(monitoringInterval);
                System.out.println("=========== Running servers =================");
                System.out.println(
                        "|" +
                        runningServers.stream()
                                .map(Monitor::serverToInfoString)
                                .collect(Collectors.joining("|")) +
                        "|"
                );

                System.out.println("=========== Pooled servers ==================");
                System.out.println(
                        "|" +
                        pooledQueue.stream()
                                .map(Server::shortDescription)
                                .collect(Collectors.joining("|")) +
                        "|"
                );
            }
        } catch (InterruptedException exc) {
            // acceptable way to stop task
        }

        System.out.println("Monitor: stopping.");
    }
}
