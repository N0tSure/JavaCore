package com.artemsirosh.tij.concurrency.balancer;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created at 21-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class LoadBalancer implements Runnable {

    private final PriorityQueue<Server> runningServers;
    private final Queue<Server> pooledQueue;
    private final int connectionLimit;
    private final int controlInterval;

    public static Builder builder() {
        return new Builder();
    }

    private LoadBalancer(
            PriorityQueue<Server> runningServers,
            Queue<Server> pooledQueue,
            int aliveConnectionLimit, int reSpawnInterval
    ) {
        this.runningServers = runningServers;
        this.connectionLimit = aliveConnectionLimit;
        this.pooledQueue = pooledQueue;
        this.controlInterval = reSpawnInterval;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(controlInterval);

                final List<Server> overloaded = findOverloadedServers();
                if (!overloaded.isEmpty()) {
                    findPooledServer().ifPresent(runningServers::add);
                } else {

                    final List<Server> idlingServers = findIdlingServers();
                    final List<Server> willBeStoppedServers;
                    if (idlingServers.size() == runningServers.size()) {
                        willBeStoppedServers = idlingServers.subList(1, idlingServers.size());
                    } else {
                        willBeStoppedServers = idlingServers;
                    }

                    willBeStoppedServers.forEach(Server::stopServing);
                    runningServers.removeAll(willBeStoppedServers);
                    pooledQueue.addAll(willBeStoppedServers);
                }
            }
        } catch (InterruptedException exc) {
            // way to terminate task
        }
    }

    private List<Server> findOverloadedServers() {
        return runningServers.stream()
                .filter(server -> server.getAliveConnections().size() > connectionLimit)
                .collect(Collectors.toList());
    }

    private Optional<Server> findPooledServer() {
        if (!pooledQueue.isEmpty()) {
            final Server server = pooledQueue.remove();
            server.startServing();
            return Optional.of(server);
        }

        return Optional.empty();
    }

    private List<Server> findIdlingServers() {
        return runningServers.stream()
                .filter(server -> server.getAliveConnections().isEmpty())
                .collect(Collectors.toList());
    }

    public static class Builder {

        private PriorityQueue<Server> runningServers;
        private Queue<Server> pooledQueue;
        private int connectionLimit;
        private int checkInterval;

        private Builder() {}

        public Builder setRunningServers(PriorityQueue<Server> runningServers) {
            this.runningServers = runningServers;
            return this;
        }

        public Builder setPooledQueue(Queue<Server> pooledQueue) {
            this.pooledQueue = pooledQueue;
            return this;
        }

        public Builder setConnectionLimit(int connectionLimit) {
            this.connectionLimit = connectionLimit;
            return this;
        }

        public Builder setCheckInterval(int checkInterval) {
            this.checkInterval = checkInterval;
            return this;
        }

        public LoadBalancer build() {
            return new LoadBalancer(
                    Objects.requireNonNull(runningServers, "Running servers queue is null"),
                    Objects.requireNonNull(pooledQueue, "Pooled servers queue is null"),
                    connectionLimit,
                    checkInterval
            );
        }
    }

}
