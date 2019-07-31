package com.artemsirosh.tij.concurrency.balancer;


import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * Created at 21-07-2019
 *
 * @author Artem Sirosh 'Artem.Sirosh@t-systems.com'
 */
public class Server implements Runnable, Comparable<Server> {

    private final BlockingQueue<Client> clients;
    private final DelayQueue<WebConnection> connectedClients;
    private final int id;

    private boolean serving;

    public Server(int id, BlockingQueue<Client> clients) {
        this.id = id;
        this.clients = clients;
        this.connectedClients = new DelayQueue<>();
        this.serving = true;
    }

    @Override
    public int compareTo(Server that) {
        return Integer.compare(this.connectedClients.size(), that.connectedClients.size());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final Client client = clients.take();
                // work with connected client
                connectedClients.put(client.connect());
                dropClosedConnections();

                synchronized (this) {
                    while (!serving)
                        wait();
                }
            }
        } catch (InterruptedException exc) {
            // way to terminate task
        }

        System.out.println(this + ": stopping.");
    }

    public List<WebConnection> getAliveConnections() {
        return ImmutableList.copyOf(connectedClients);
    }

    public synchronized void startServing() {
        serving = true;
        notifyAll();
    }

    /**
     * Stop serving and drop all connections.
     */
    public synchronized void stopServing() {
        serving = false;
        connectedClients.clear();
    }

    public String shortDescription() {
        return String.format("S%d", id);
    }

    private void dropClosedConnections() {
        WebConnection webConnection = connectedClients.poll();
        while (webConnection != null)
            webConnection = connectedClients.poll();

    }

    @Override
    public String toString() {
        return String.format("Server #%1$d [%2$sline]", id, serving ? "on" : "off");
    }
}
