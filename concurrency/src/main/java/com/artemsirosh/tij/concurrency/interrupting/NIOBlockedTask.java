package com.artemsirosh.tij.concurrency.interrupting;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

/**
 * Created at 20-06-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class NIOBlockedTask implements Runnable {

    private final SocketChannel channel;

    NIOBlockedTask(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + ": reading input.");
            channel.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException exc) {
            System.out.println(this + ": closed by interruption.");
        } catch (AsynchronousCloseException exc) {
            System.out.println(this + ": input closed by another thread.");
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[blocked by " + channel.getClass().getSimpleName() + "]";
    }
}
