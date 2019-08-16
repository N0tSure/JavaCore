package com.artemsirosh.tij.concurrency.balancer;

import com.google.common.collect.ImmutableList;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

/**
 * Created at 29-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Monitor implements Runnable {

    private final PriorityQueue<Server> runningServers;
    private final Queue<Server> pooledServers;
    private final IntSupplier clientQueueSizeSupplier;
    private final int monitoringInterval;

    private static String serverToInfoString(Server server) {
        return String.format("%s:%d", server.shortDescription(), server.getAliveConnections().size());
    }

    Monitor(
            PriorityQueue<Server> runningServers,
            Queue<Server> pooledServers,
            IntSupplier clientQueueSizeSupplier,
            int monitoringInterval) {
        this.runningServers = runningServers;
        this.pooledServers = pooledServers;
        this.clientQueueSizeSupplier = clientQueueSizeSupplier;
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
                                ImmutableList.copyOf(runningServers).stream()
                                .map(Monitor::serverToInfoString)
                                .collect(Collectors.joining("|")) +
                        "|"
                );

                System.out.println("=========== Pooled servers ==================");
                System.out.println(
                        "|" +
                        ImmutableList.copyOf(pooledServers).stream()
                                .map(Server::shortDescription)
                                .collect(Collectors.joining("|")) +
                        "|"
                );

                System.out.println("========== Client queue size ===============");
                System.out.println(clientQueueSizeSupplier.getAsInt());
                System.out.println("\n");
            }
        } catch (InterruptedException exc) {
            // acceptable way to stop task
        }

        System.out.println("Monitor: stopping.");
    }
}
