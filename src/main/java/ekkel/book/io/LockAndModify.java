package ekkel.book.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created on 17 Jun, 2017.
 * Lock and modify mapped file buffer
 * @author Artemis A. Sirosh
 */
public class LockAndModify implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(LockAndModify.class);

    private FileChannel channel;
    private ByteBuffer buffer;
    private int start, end;

    public LockAndModify(ByteBuffer buffer, FileChannel channel, int start, int end) {

        this.start = start;
        this.end = end;
        this.channel = channel;

        buffer.limit(end);
        buffer.position(start);
        this.buffer = buffer.slice();
    }

    @Override
    public void run() {
        try {
            FileLock lock = channel.lock(start, end, false);
            LOGGER.info("Locked: {} to {} at {}", start, end, System.nanoTime());

            while (buffer.position() < buffer.limit() - 1) {
                buffer.put((byte) (buffer.get() + 1));
            }

            lock.release();

            LOGGER.info("Released: {} to {} at {}", start, end, System.nanoTime());
        } catch (IOException exc) {
            LOGGER.error("Error occur due locking and modification", exc);
        }

    }
}
