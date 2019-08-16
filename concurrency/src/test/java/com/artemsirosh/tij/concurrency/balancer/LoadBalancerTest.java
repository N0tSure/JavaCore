package com.artemsirosh.tij.concurrency.balancer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created at 22-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
@ExtendWith(MockitoExtension.class)
class LoadBalancerTest {

    @Test
    @DisplayName("Should detect overloaded server and fire up pooled one")
    void shouldUsePooledServer(
            @Mock Server overLoadedServer, @Mock Server runningServer, @Mock Server pooledServer
    ) throws InterruptedException {

        final PriorityQueue<Server> runningQueue = serversToPriorityQueue(overLoadedServer, runningServer);
        final Queue<Server> pooledQueue = new LinkedList<>(Collections.singletonList(pooledServer));
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final LoadBalancer balancer = LoadBalancer.builder()
                .setRunningServers(runningQueue)
                .setPooledQueue(pooledQueue)
                .setCheckInterval(100)
                .setConnectionLimit(5)
                .build();

        Mockito.lenient().when(overLoadedServer.getAliveConnections()).thenReturn(generateWebConnections(6));
        Mockito.lenient().when(runningServer.getAliveConnections()).thenReturn(generateWebConnections(4));

        exec.execute(balancer);
        TimeUnit.MILLISECONDS.sleep(150);
        exec.shutdownNow();

        Mockito.verify(overLoadedServer).getAliveConnections();
        Mockito.verify(pooledServer).startServing();
        Assertions.assertTrue(pooledQueue.isEmpty());
    }

    @Test
    @DisplayName("Should keep running a single server")
    void theLastRunningServerPreserve(@Mock Server firstIdlingServer, @Mock Server secondIdlingServer) throws InterruptedException {

        final PriorityQueue<Server> runningServers = serversToPriorityQueue(firstIdlingServer, secondIdlingServer);
        final Queue<Server> pooledQueue = new LinkedList<>();
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final LoadBalancer balancer = LoadBalancer.builder()
                .setRunningServers(runningServers)
                .setPooledQueue(pooledQueue)
                .setCheckInterval(100)
                .setConnectionLimit(5)
                .build();

        Mockito.when(firstIdlingServer.getAliveConnections()).thenReturn(generateWebConnections(0));
        Mockito.when(secondIdlingServer.getAliveConnections()).thenReturn(generateWebConnections(0));

        executorService.execute(balancer);
        TimeUnit.MILLISECONDS.sleep(150);
        executorService.shutdownNow();

        Assertions.assertFalse(runningServers.isEmpty());
    }

    @Test
    @DisplayName("Should detect idling server and cool down it to pool")
    void shouldDetectIdlingServer(
            @Mock Server runningServer, @Mock Server idlingServer, @Mock Server pooledServer
    ) throws InterruptedException {

        final PriorityQueue<Server> runningQueue = serversToPriorityQueue(runningServer, idlingServer);
        final Queue<Server> pooledQueue = new LinkedList<>(Collections.singletonList(pooledServer));
        final ExecutorService exec = Executors.newSingleThreadExecutor();
        final LoadBalancer balancer = LoadBalancer.builder()
                .setRunningServers(runningQueue)
                .setPooledQueue(pooledQueue)
                .setCheckInterval(100)
                .setConnectionLimit(5)
                .build();

        Mockito.lenient().when(runningServer.getAliveConnections()).thenReturn(generateWebConnections(4));
        Mockito.when(idlingServer.getAliveConnections()).thenReturn(generateWebConnections(0));

        exec.execute(balancer);
        TimeUnit.MILLISECONDS.sleep(150);
        exec.shutdownNow();

        Mockito.verify(idlingServer, Mockito.atLeastOnce()).getAliveConnections();
        Mockito.verify(idlingServer).stopServing();
        Assertions.assertEquals(2, pooledQueue.size());
        Assertions.assertEquals(1, runningQueue.size());
    }

//    @Test
//    void monitorPrintingTest(
//            @Mock Server overLoadedServer, @Mock Server runningServer, @Mock Server pooledServer
//    ) throws InterruptedException {
//        final PriorityQueue<Server> runningQueue = serversToPriorityQueue(overLoadedServer, runningServer);
//        final Queue<Server> pooledQueue = new LinkedList<>(Collections.singletonList(pooledServer));
//        final Monitor monitor = new Monitor(runningQueue, pooledQueue, 50);
//        final ExecutorService exec = Executors.newSingleThreadExecutor();
//
//        Mockito.when(overLoadedServer.getAliveConnections()).thenReturn(generateWebConnections(6));
//        Mockito.when(overLoadedServer.shortDescription()).thenReturn("S1");
//
//        Mockito.when(runningServer.getAliveConnections()).thenReturn(generateWebConnections(4));
//        Mockito.when(runningServer.shortDescription()).thenReturn("S2");
//
//        Mockito.when(pooledServer.shortDescription()).thenReturn("S3");
//
//        exec.execute(monitor);
//        TimeUnit.SECONDS.sleep(1);
//        exec.shutdownNow();
//
//
//    }

    private static PriorityQueue<Server> serversToPriorityQueue(Server... servers) {
        return Arrays.stream(servers).collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);
    }

    private static List<WebConnection> generateWebConnections(int quantity) {
        return Stream.generate(LoadBalancerTest::factorMockWebConnection)
                .limit(quantity)
                .collect(Collectors.toList());
    }

    private static WebConnection factorMockWebConnection() {
        return Mockito.mock(WebConnection.class);
    }
}
