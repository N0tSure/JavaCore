package ekkel.book.io;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created on 12 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class MappedIOPerformance {
    private static final int INTS_NUM = 4_000_000;
    private static final int BUFFS_NUM = 200_000;
    private static final String TEMPORAL_FILE = "temp.tmp";


    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void streamWriteTest() throws Exception {
        try(DataOutputStream outputStream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(new File(TEMPORAL_FILE))
                )
        )) {
            for (int i = 0; i < INTS_NUM; i++) {
                outputStream.writeInt(i);
            }
        }
    }


    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void mappedWriteTest() throws Exception {
        FileChannel channel = new RandomAccessFile(TEMPORAL_FILE, "rw").getChannel();
        IntBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size()).asIntBuffer();
        for (int i = 0; i < INTS_NUM; i++) {
            buffer.put(i);
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void streamReadTest() throws Exception {
        try (DataInputStream inputStream = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(TEMPORAL_FILE)
                ))
        ) {
            for (int i = 0; i < INTS_NUM; i++) {
                inputStream.readInt();
            }
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void mappedReadTest() throws Exception {
        try (FileChannel channel = new FileInputStream(TEMPORAL_FILE).getChannel()) {

            IntBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size()).asIntBuffer();

            while (buffer.hasRemaining())
                buffer.get();
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void streamReadWriteTest() throws Exception {
        try (RandomAccessFile access = new RandomAccessFile(TEMPORAL_FILE, "rw")) {
            access.writeInt(1);
            for (int i = 0; i < BUFFS_NUM; i++) {
                access.seek(access.length() - 4);
                access.writeInt(access.readInt());
            }
        }
    }

    @Benchmark
    @Fork(3)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void mappedReadWrite() throws Exception {
        try (FileChannel channel = new RandomAccessFile(TEMPORAL_FILE, "rw").getChannel()) {
            IntBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size()).asIntBuffer();
            buffer.put(0);
            for (int i = 1; i < BUFFS_NUM; i++) {
                buffer.put(buffer.get(i - 1));
            }
        }
    }
}
