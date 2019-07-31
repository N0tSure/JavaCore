package com.artemsirosh.tij.concurrency.balancer;

import com.artemsirosh.tij.concurrency.FinisherTask;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created at 29-07-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
public class LoadBalancerDemo {

    public static void main(String[] args) throws  InterruptedException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        final int numberOfServers = 5;
        final int connectionLimit = 5;
        final int reSpawnInterval = 100;
        final int maxSeviceTime = 1000;
        final int controlInterval = 100;
        final int monitoringInterval = 100;

        final Queue<Server> pooledQueue = new LinkedList<>();
        final BlockingQueue<Client> clients = new LinkedBlockingQueue<>();
        final  ClientGenerator generator = ClientGenerator.builder()
                .setClientsQueue(clients)
                .setReSpawnInterval(reSpawnInterval)
                .setMaxServiceTime(maxSeviceTime)
                .setRandom(new Random(42))
                .build();
        exec.execute(generator);

        final PriorityQueue<Server> runningServers = IntStream.range(0, numberOfServers)
                .mapToObj(i -> new Server(i, clients))
                .peek(exec::execute)
                .collect(Collectors.toCollection(PriorityQueue::new));

        final LoadBalancer loadBalancer = LoadBalancer.builder()
                .setRunningServers(runningServers)
                .setPooledQueue(pooledQueue)
                .setCheckInterval(controlInterval)
                .setConnectionLimit(connectionLimit)
                .build();
        exec.execute(loadBalancer);

        final Monitor monitor = new Monitor(runningServers, pooledQueue, monitoringInterval);
        exec.execute(monitor);

        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
