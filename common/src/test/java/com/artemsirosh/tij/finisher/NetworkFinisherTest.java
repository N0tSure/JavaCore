package com.artemsirosh.tij.finisher;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * Created at 16-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class NetworkFinisherTest {

    private static final InetSocketAddress ADDRESS = new InetSocketAddress(8080);
    private NetworkFinisher<Integer> finisher;
    private ExecutorService executorService;
    private Future<Integer> finisherFuture;

    @Before
    public void setUp() {
        finisher = new NetworkFinisher<>(ADDRESS);
        executorService = Executors.newCachedThreadPool();
        finisherFuture = executorService.submit(finisher);
    }

    @After
    public void tearDown() {
        executorService.shutdownNow();
    }

    @Test
    public void shouldGetIncomingConnection() throws ExecutionException, InterruptedException {

        final long startMills = System.currentTimeMillis();
        executorService.execute(() -> testNioClient(ADDRESS));
        finisherFuture.get();
        final long durationMills = System.currentTimeMillis() - startMills;

        System.out.println("Duration: " + durationMills + " mills.");
        Assert.assertTrue(durationMills >= 100);
    }

    @Test
    public void shouldReturnProperValue() throws ExecutionException, InterruptedException {
        finisher.setReturnValue(42);
        executorService.execute(() -> testNioClient(ADDRESS));

        Assert.assertEquals(new Integer(42), finisherFuture.get());
    }

    @Test
    public void shouldShutdownBySignal() throws ExecutionException, InterruptedException {
        final long startMills = System.currentTimeMillis();
        executorService.execute(() -> interruptAfterTimeout(finisher, null));
        executorService.execute(() -> testNioClient(ADDRESS, TimeUnit.SECONDS, 5));
        finisherFuture.get();

        final long durationMills = System.currentTimeMillis() - startMills;
        System.out.println("Duration: " + durationMills + " mills.");
        Assert.assertTrue(durationMills >= 100 && durationMills < 5000);
    }

    @Test
    public void shouldShutdownBySignalAndReturnLastValue() throws ExecutionException, InterruptedException {
        executorService.execute(() -> interruptAfterTimeout(finisher, 47));
        executorService.execute(() -> testNioClient(ADDRESS, TimeUnit.SECONDS, 1));

        Assert.assertEquals(new Integer(47), finisherFuture.get());
    }

    private static <T> void interruptAfterTimeout(Finisher<T> finisher, T value) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            if (value != null) {
                finisher.shutdown(value);
            } else {
                finisher.shutdown();
            }
        } catch (InterruptedException exc) {
            Assert.fail("Waiting interrupted.");
        }
    }

    private static void testNioClient(InetSocketAddress address) {
        testNioClient(address, TimeUnit.MILLISECONDS, 100);
    }

    private static void testNioClient(InetSocketAddress address, TimeUnit timeUnit, long timeout) {
        final Charset charset = StandardCharsets.UTF_8;
        try {
            timeUnit.sleep(timeout);
            try (SocketChannel channel = SocketChannel.open(address)) {
                final String request = "GET / HTTP/1.1" + '\n' +
                        "Host: " + address.getHostString() + ":" + address.getPort() + "\n\n";

                System.out.println(channel.write(charset.encode(request)) + " bytes was written.");

                final ByteBuffer responseBuffer = ByteBuffer.allocate(20000);
                channel.read(responseBuffer);
                responseBuffer.rewind();
                System.out.println("Server response: \n" + charset.decode(responseBuffer).toString().trim());

            }
        } catch (InterruptedException | IOException exc) {
            // way to exit
        }
    }
}
