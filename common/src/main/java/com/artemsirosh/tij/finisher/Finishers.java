package com.artemsirosh.tij.finisher;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created at 19-09-2019
 *
 * {@link Finishers} is a helping component, which aim is instantiate various
 * implementation of finishers. Available finishers:
 *
 * <table>
 *     <tr>
 *         <th>Finisher type</th>
 *         <th>Description</th>
 *     </tr>
 *     <tr>
 *         <td>TimeoutFinisher</td>
 *         <td>Resolves after time out</td>
 *     </tr>
 *     <tr>
 *         <td>NetworkFinisher</td>
 *         <td>Resolves after receiving request over network interface</td>
 *     </tr>
 *     <tr>
 *         <td>JMXFinisher</td>
 *         <td>Resolves after get signal over JMX</td>
 *     </tr>
 * </table>
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 * @see Finisher
 */
public final class Finishers {

    /**
     * Creates a new instance of timeout finisher, which
     * {@link java.util.concurrent.Future} resolves after timeout given in
     * method parameters.
     * @param timeUnit unit of timeout
     * @param timeout time in given units
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     */
    public static <T> Finisher<T> newTimeoutFinisher(TimeUnit timeUnit, long timeout) {
        return new TimeoutFinisher<>(timeUnit, timeout);
    }

    /**
     * Creates a new instance of {@link Finisher} same as
     * {@link #newTimeoutFinisher(TimeUnit, long)} with next params
     * <pre>
     *     Finishers.newTimeoutFinisher(TimeUnit.MILLISECONDS, mills)
     * </pre>
     * @param mills timeout in milliseconds
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     */
    public static <T> Finisher<T> newMillsTimeoutFinisher(long mills) {
        return newTimeoutFinisher(TimeUnit.MILLISECONDS, mills);
    }

    /**
     * Creates a new instance of {@link Finisher} same as
     * {@link #newTimeoutFinisher(TimeUnit, long)} with next params
     * <pre>
     *     Finishers.newTimeoutFinisher(TimeUnit.SECONDS, seconds)
     * </pre>
     * @param seconds timeout in seconds
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     */
    public static <T> Finisher<T> newSecondsTimeoutFinisher(long seconds) {
        return newTimeoutFinisher(TimeUnit.SECONDS, seconds);
    }

    /**
     * Creates a new instance of {@link Finisher} that can receive signal
     * using network. Signal is any incoming data received by {@link Finisher},
     * that listens given port of given host.
     * @param hostname the Host name
     * @param port port number
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     * @see InetSocketAddress
     */
    public static <T> Finisher<T> newNetworkFinisher(String hostname, int port) {
        return new NetworkFinisher<>(new InetSocketAddress(hostname, port));
    }

    /**
     * Creates a new instance of {@link Finisher} that can receive signal using
     * network. Signal is any incoming data received by {@link Finisher}, that
     * listens given port on localhost.
     * @param port port number
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     */
    public static <T> Finisher<T> newNetworkFinisher(int port) {
        return new NetworkFinisher<>(new InetSocketAddress(port));
    }

    /**
     * Creates a new {@link Finisher} instance that can receive signal
     * using <b>JMX</b> protocol. A new instance will be already registered
     * using <i>platform MBean server</i>, it's possible to create multiple
     * finishers: they just should have different task names, after a finisher's
     * {@link java.util.concurrent.Future} will resolved finisher may be
     * unregistered and can created a new finisher for given task.
     *
     * @param taskName name of task
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     * @throws IllegalArgumentException if given task name is violate MBeans rules
     * @throws IllegalStateException when finisher for given task already exists
     * @throws RuntimeException in case of error due MBean registration
     *
     * @see JMXFinisherMBean
     * @see MBeanServer#registerMBean(Object, ObjectName)
     * @see ObjectName
     */
    public static <T> Finisher<T> newJMXFinisher(String taskName) {
        final JMXFinisher<T> finisher = new JMXFinisher<>(
                new BlockingReturnValueHolder<>(),
                new ReturnValueHolder<>(),
                taskName
        );

        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.registerMBean(finisher, createJMXFinisherObjectName(taskName));
        } catch (InstanceAlreadyExistsException exc) {
            throw new IllegalStateException("Finisher for that task name already exists. Try unregister it.");
        } catch (MBeanRegistrationException | NotCompliantMBeanException exc) {
            throw new RuntimeException(exc);
        }

        return finisher;
    }

    /**
     * Creates a <b>JMX</b> {@link Finisher} same as
     * {@link #newJMXFinisher(String)}, but with random task name.
     * @param <T> type of finisher's return value
     * @return instance of {@link Finisher}
     */
    public static <T> Finisher<T> newJMXFinisher() {
        return newJMXFinisher(UUID.randomUUID().toString());
    }

    /**
     * Allows unregister <b>MBean</b> which was previously registered.
     * @param mBean registered MBean finisher
     * @throws IllegalArgumentException if given task name is violate MBeans rules
     * @throws IllegalStateException if given MBean not registered
     */
    public static void unregisterJMXFinisherMbean(JMXFinisherMBean mBean) {

        final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            mBeanServer.unregisterMBean(createJMXFinisherObjectName(mBean.getTaskName()));
        } catch (InstanceNotFoundException exc) {
            throw new IllegalStateException(
                    "JMXFinisherMBean with task name '" + mBean.getTaskName() + "' not registered."
            );
        } catch (MBeanRegistrationException exc) {
            throw new RuntimeException(exc);
        }
    }

    private static ObjectName createJMXFinisherObjectName(String taskName) {
        try {
            return new ObjectName(
                    String.format("com.artemsirosh.tij.finisher:type=JMXFinisher,name=%s", taskName)
            );
        } catch (MalformedObjectNameException exc) {
            throw new IllegalArgumentException("Wrong task name: " + taskName, exc);
        }
    }

    private Finishers() {
        // cannot instantiate
    }
}
