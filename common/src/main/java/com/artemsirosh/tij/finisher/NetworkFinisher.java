package com.artemsirosh.tij.finisher;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created at 16-09-2019
 *
 * This type of {@link Finisher} can receive signal using TCP/IP, any request
 * causes {@link java.util.concurrent.Future} resolving.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class NetworkFinisher<T> implements Finisher<T> {

    private static final String RESPONSE_PAYLOAD_TEMPLATE = "<!doctype html>" +
            "<head><meta charset=\"UTF-8\" content=\"text/html\"/>" +
            "<title>Shutdown signal received</title>" +
            "</head>" +
            "<body><h1>Shutdown signal received</h1><p>Date: %1$s</p>" +
            "</body></html>";

    private final InetSocketAddress address;
    private final ReturnValueHolder<T> returnValueHolder;
    private final ProcessingThreadHolder processingThreadHolder;
    private final Charset defaultCharset = StandardCharsets.UTF_8;

    public NetworkFinisher(InetSocketAddress address) {
        this.address = address;
        this.returnValueHolder = new ReturnValueHolder<>();
        this.processingThreadHolder = new ProcessingThreadHolder();
    }

    @Override
    public void setReturnValue(T t) {
        returnValueHolder.setValue(t);
    }

    @Override
    public Optional<T> getReturnValue() {
        return returnValueHolder.getValue();
    }

    @Override
    public void shutdown() {
        processingThreadHolder.interruptHoldingThread();
    }

    @Override
    public void shutdown(T t) {
        returnValueHolder.setValue(t);
        processingThreadHolder.interruptHoldingThread();
    }

    @Override
    public T call() throws Exception {
        processingThreadHolder.holdCurrentThread();
        try (final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
            serverSocketChannel.bind(address);
            try (final SocketChannel channel = serverSocketChannel.accept()) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                System.out.println("Bytes read: " + channel.read(byteBuffer) + ".");
                byteBuffer.rewind();

                final String response = "HTTP/1.1 200 OK\n" +
                        "Content-Type: text/html\n" +
                        "Content-Encoding: UTF-8\n\n" +
                        getResponsePayload();
                channel.write(defaultCharset.encode(response));
            }
        } catch (ClosedByInterruptException exc) {
            // acceptable way to exit
        }

        return returnValueHolder.getValue().orElse(null);
    }

    private String getResponsePayload() {
        return String.format(RESPONSE_PAYLOAD_TEMPLATE, LocalDateTime.now());
    }
}
