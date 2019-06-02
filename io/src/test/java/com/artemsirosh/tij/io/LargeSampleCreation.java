package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.TextFile;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertThat;

/**
 * Created on 04 Jun, 2017.
 * Using for large sample file generation
 * @author Artemis A. Sirosh
 */
public class LargeSampleCreation {
    private static final Logger LOGGER = LoggerFactory.getLogger(LargeSampleCreation.class);
    private static final int FILE_LENGTH = 0x8fffff;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private static List<String> shakespearLine;
    private static Random random;

    @BeforeClass
    public static void setUpShakespaer() throws Exception {
        shakespearLine = new TextFile("/home/train/Core/src/main/resources/n_asci.sample");
        random = new Random();

        LOGGER.info("William Shakespear's lines read: {}", shakespearLine.size());
    }

    private static String getRandomShakespear() {
        StringBuilder result = new StringBuilder(shakespearLine.get(random.nextInt(shakespearLine.size())));
        result.trimToSize();
        result.append('\n');
        return result.toString();
    }


    @Test
    public void fakeSampleSaving() throws Exception {
        File tmp = temporaryFolder.newFile();
        CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
        CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();
        String shakespear = TextFile.read("/home/train/Core/src/main/resources/n_asci.sample");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        encoder.encode(CharBuffer.wrap(shakespear), buffer, false);

        LOGGER.info("Buffer after encoding: {}", buffer);
        buffer.flip();
        LOGGER.info("Buffer after flipping: {}", buffer);


        MappedByteBuffer output = new RandomAccessFile(tmp, "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, 2048);

        while (output.remaining() > buffer.limit()) {
            LOGGER.info("Mapped file before writing: {}", output);
            output.put(buffer);
            LOGGER.info("Mapped file after writing: {}", output);
            buffer.flip();
        }

        LOGGER.info("Bytes has been written: {}", output.position());
        output.flip();

        assertThat(decoder.decode(output).toString(), CoreMatchers.containsString(shakespear));

    }

    @Test
    public void bufferManipulation() throws Exception {
        String shakespear = TextFile.read("/home/train/Core/src/main/resources/n_asci.sample");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        LOGGER.info("Buffer: {}", buffer);

        CharBuffer charBuffer = CharBuffer.wrap(shakespear);
        CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();

        encoder.encode(charBuffer, buffer, false);

        CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();
        buffer.flip();
        LOGGER.info("Decoded:\n {}", decoder.decode(buffer));

    }

    @Test
    public void realSampleCreation() throws Exception {
        final File sample = new File("/home/train/Core/src/main/resources/large.sample");
        final String shakespear = TextFile.read("/home/train/Core/src/main/resources/n_asci.sample");
        CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        encoder.encode(CharBuffer.wrap(shakespear), buffer, false);
        buffer.flip();

        MappedByteBuffer output = new RandomAccessFile(sample, "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, FILE_LENGTH);

        for (long i = 0; i < 1_000_000; i++)
            ;

        while (output.remaining() > buffer.limit()) {
            output.put(buffer);
            buffer.flip();
        }

        LOGGER.info("Finished. Bytes was written: {}", output.position());
    }

}
