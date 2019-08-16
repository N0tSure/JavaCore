package com.artemsirosh.tij.concurrency.balancer;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created at 19-07-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ClientGenerator implements Runnable {

    private final BlockingQueue<Client> clients;
    private final int reSpawnInterval;
    private final int maxServiceTime;
    private final Random random;

    public static Builder builder() {
        return new Builder();
    }

    private ClientGenerator(BlockingQueue<Client> clients, int reSpawnInterval, int maxServiceTime, Random random) {
        this.clients = clients;
        this.reSpawnInterval = reSpawnInterval;
        this.maxServiceTime = maxServiceTime;
        this.random = random;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(reSpawnInterval);
                clients.put(new Client(random.nextInt(maxServiceTime)));
            }
        } catch (InterruptedException exc) {
            // way to terminate task
        }

        System.out.println(this + ": stopped.");
    }

    @Override
    public String toString() {
        return "ClientGenerator";
    }

    public static class Builder {
        private BlockingQueue<Client> clients;
        private int reSpawnInterval;
        private int maxServiceTime;
        private Random random;

        public Builder setClientsQueue(BlockingQueue<Client> clientsQueue) {
            this.clients = clientsQueue;
            return this;
        }

        public Builder setReSpawnInterval(int reSpawnInterval) {
            this.reSpawnInterval = reSpawnInterval;
            return this;
        }

        public Builder setMaxServiceTime(int maxServiceTime) {
            this.maxServiceTime = maxServiceTime;
            return this;
        }

        public Builder setRandom(Random random) {
            this.random = random;
            return this;
        }

        public ClientGenerator build() {
            return new ClientGenerator(
                    Objects.requireNonNull(clients, "Client queue in null"),
                    reSpawnInterval,
                    maxServiceTime,
                    Objects.requireNonNull(random, "Random is null.")
            );
        }
    }
}
