package com.artemsirosh.tij.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Created on 17 Jun, 2017.
 * 
 * @author Artemis A. Sirosh
 */
public class FileLockingTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileLockingTest.class);
    
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    
    @Test
    public void simpleLockingFile() throws Exception {

        File tmp = temporaryFolder.newFile();
        try (FileChannel channel = new FileOutputStream(tmp).getChannel()) {
            FileLock lock = channel.tryLock();
            if (lock != null) {
                LOGGER.info("Locked file: {}, lock acquired by {}", tmp, lock.acquiredBy());
                TimeUnit.SECONDS.sleep(2);
                lock.release();
                LOGGER.info("Lock released");
            }
        }
    }

    @Test
    public void lockAndModifyTest() throws Exception {

        File tmp = temporaryFolder.newFile();
        int length = 0x8ffffff;
        try (FileChannel channel = new RandomAccessFile(tmp, "rw").getChannel()) {
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
            for (int i = 0; i < 0x8ffffff; i++) {
                buffer.put((byte) 'x');
            }

            Thread first = new Thread(new LockAndModify(buffer, channel, 0, length/2));
            Thread second = new Thread(new LockAndModify(buffer, channel, length/2 + 1, length));

            first.start();
            second.start();

            first.join();
            second.join();
        }
    }
    
}
