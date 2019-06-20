package com.artemsirosh.tij.concurrency.interrupting;

import com.artemsirosh.tij.concurrency.SleeperTask;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created at 19-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class InterruptingTest {

    private static void interruptTask(Runnable runnable, ExecutorService executor) throws InterruptedException {

        Future<?> future = executor.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println("Interrupting " + runnable);
        future.cancel(true);
        System.out.println("Interrupt sent to " + runnable);
    }

    @Test
    @DisplayName("Trying to interrupt threads blocked by different causes")
    void interruptingBlockedThread() throws InterruptedException {
        final ExecutorService service = Executors.newCachedThreadPool();

        interruptTask(new SleeperTask(TimeUnit.SECONDS, 10), service);
        interruptTask(new IOBlockedTask(System.in), service);
        interruptTask(new SynchronizedBlockedTask(), service);
        service.shutdown();
    }

    @Test
    @DisplayName("Interrupting threads by closing resources that blocking it")
    void interruptingByClosingResource() throws IOException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();

        final ServerSocket serverSocket = new ServerSocket(0);
        final InputStream inputStream = new Socket("localhost", serverSocket.getLocalPort()).getInputStream();

        executor.execute(new IOBlockedTask(inputStream));

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down thread pool.");
        executor.shutdownNow();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(inputStream.getClass().getSimpleName() + ": closing.");
        inputStream.close();

    }

    @Test
    @DisplayName("Interrupting thread blocked by NIO resource")
    void interruptingNioBlockedThread() throws IOException, InterruptedException {
        final ExecutorService executor = Executors.newCachedThreadPool();
        final ServerSocket serverSocket = new ServerSocket(0);
        final InetSocketAddress address = new InetSocketAddress("localhost", serverSocket.getLocalPort());

        SocketChannel channel1 = SocketChannel.open(address);
        SocketChannel channel2 = SocketChannel.open(address);

        Future<?> future = executor.submit(new NIOBlockedTask(channel1));
        executor.execute(new NIOBlockedTask(channel2));
        executor.shutdown();

        TimeUnit.SECONDS.sleep(1);
        future.cancel(true);

        TimeUnit.SECONDS.sleep(1);
        channel2.close();
    }

    @Test
    @DisplayName("Interrupting thread blocked by mutex")
    void interruptingMutexBlocked() throws InterruptedException {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new MutexBlockedTask());

        TimeUnit.SECONDS.sleep(1);
        System.out.println("Interrupting thread.");
        executorService.shutdownNow();
    }
}
