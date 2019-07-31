package com.artemsirosh.tij.concurrency.balancer;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created at 19-07-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
public class Client {

    private final int requestProcessingTime;

    Client(int processingTimeMills) {
        this.requestProcessingTime = processingTimeMills;
    }

    WebConnection connect() {
        return new ClientWebConnection(requestProcessingTime);
    }

    @Override
    public String toString() {
        return String.format("[%d]", requestProcessingTime);
    }

    private static final class ClientWebConnection implements WebConnection {

        private final long processingDelay;

        private ClientWebConnection(int processingTimeMills) {
            processingDelay =
                    System.nanoTime() + TimeUnit.NANOSECONDS.convert(processingTimeMills, TimeUnit.MILLISECONDS);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(System.nanoTime() - processingDelay, TimeUnit.NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            ClientWebConnection that = (ClientWebConnection) o;
            return Long.compare(this.processingDelay, that.processingDelay);
        }
    }
}
