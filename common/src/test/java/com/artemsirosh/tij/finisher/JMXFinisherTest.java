package com.artemsirosh.tij.finisher;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created at 18-09-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class JMXFinisherTest {

    private ExecutorService executor;
    private MBeanServer mBeanServer;
    private ObjectName objectName;
    private String taskName;
    private JMXFinisherMBean finisherProxy;

    @Before
    public void setUp() throws Exception {
        executor = Executors.newCachedThreadPool();
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        taskName = UUID.randomUUID().toString();
        objectName = new ObjectName("com.artemsirosh.tij.finisher:type=JMXFinisher,name=" + taskName);
        finisherProxy = JMX.newMBeanProxy(mBeanServer, objectName, JMXFinisherMBean.class);
    }

    @After
    public void tearDown() throws Exception {
        try {
            mBeanServer.unregisterMBean(objectName);
        } catch (InstanceNotFoundException exc) {
            // don't care
        }
        executor.shutdownNow();
    }

    @Test
    public void shouldReceiveShutdownSignal() throws InterruptedException, ExecutionException, TimeoutException {
        final JMXFinisher<?> finisher = createJMXFinisher(taskName);
        final Future<?> future = executor.submit(finisher);

        executor.execute(() -> invokeAfterTimeout(finisher, null));
        future.get(5, TimeUnit.SECONDS);
    }

    @Test
    public void shouldReceiveShutdownSignalAndReturnValue() throws ExecutionException, InterruptedException {
        final JMXFinisher<String> finisher = createJMXFinisher(taskName);
        final Future<String> future = executor.submit(finisher);
        finisher.setReturnValue("foo");

        executor.execute(() -> invokeAfterTimeout(finisher, null));
        Assert.assertEquals("foo", future.get());
    }

    @Test
    public void shouldReceiveShutdownSignalAndReturnTheLastValue() throws ExecutionException, InterruptedException {
        final JMXFinisher<String> finisher = createJMXFinisher(taskName);
        final Future<String> future = executor.submit(finisher);
        finisher.setReturnValue("foo");

        executor.execute(() -> invokeAfterTimeout(finisher, "foo bar baz"));
        Assert.assertEquals("foo bar baz", future.get());
    }

    @Test
    public void shouldReturnTaskName() throws Exception {
        final JMXFinisher<?> finisher = createJMXFinisher(taskName);
        registerMBean(mBeanServer, objectName, finisher);

        Assert.assertEquals(taskName, finisherProxy.getTaskName());
    }

    @Test
    public void shouldAcceptJMXSignal() throws Exception {
        final JMXFinisher<?> finisher = createJMXFinisher(taskName);
        registerMBean(mBeanServer, objectName, finisher);
        final Future<?> future = executor.submit(finisher);

        executor.execute(() -> invokeJMXProxyAfterTimeout(finisherProxy));
        future.get(5, TimeUnit.SECONDS);
    }

    private static <T> JMXFinisher<T> createJMXFinisher(String taskName) {
        return new JMXFinisher<>(
                new BlockingReturnValueHolder<>(),
                new ReturnValueHolder<>(),
                taskName
        );
    }

    private static void invokeJMXProxyAfterTimeout(JMXFinisherMBean finisherProxy) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            finisherProxy.finishTask();
        } catch (InterruptedException exc) {
            Assert.fail("Sleep has been interrupted: sanity check fail.");
        }
    }

    private static <T> void invokeAfterTimeout(JMXFinisher<T> finisher, T value) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            if (value == null) {
                finisher.shutdown();
            } else {
                finisher.shutdown(value);
            }
        } catch (InterruptedException exc) {
            Assert.fail("Sleep has been interrupted: sanity check fail.");
        }
    }

    private static <T> void registerMBean(
            final MBeanServer mBeanServer, final ObjectName objectName, final JMXFinisher<T> jmxFinisher
    ) throws Exception {
        mBeanServer.registerMBean(jmxFinisher, objectName);
    }
}
