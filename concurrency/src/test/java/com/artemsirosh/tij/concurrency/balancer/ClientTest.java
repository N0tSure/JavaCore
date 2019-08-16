package com.artemsirosh.tij.concurrency.balancer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created at 16-08-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ClientTest {

    @Test
    void shouldProvideProperWebConnection() {
        final Client client = new Client(100_000);
        final WebConnection connection = client.connect();

        Assertions.assertTrue(connection.getDelay(TimeUnit.MILLISECONDS) > 0);
        Assertions.assertTrue(connection.getDelay(TimeUnit.MICROSECONDS) > 0);
    }
}
