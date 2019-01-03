package ekkel.book.io;

import ekkel.book.util.TextFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.zip.*;

import static org.junit.Assert.assertEquals;

/**
 * Created on 18 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class FileCompressingTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileCompressingTest.class);
    private static final File RESOURCES = new File("/home/train/Core/src/main/resources");

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(RESOURCES);
    private File compressedFile = new File(RESOURCES, "ascii.sample.gz");
    private File asciiSample = new File(RESOURCES, "asci.sample");

    @Before
    public void compressFileInGzip() throws Exception {
        LOGGER.info("Original file: {}, size: {}", asciiSample, asciiSample.length());

        try (FileChannel inputChannel = new FileInputStream(asciiSample).getChannel();
             WritableByteChannel outputChannel = Channels.newChannel(
                     new GZIPOutputStream(new FileOutputStream(compressedFile)))) {

            inputChannel.transferTo(0, inputChannel.size(), outputChannel);

        }
        LOGGER.info("File compressed: {}, size: {}", compressedFile, compressedFile.length());
    }

    @After
    public void tearDown() throws Exception {
        compressedFile.delete();
    }

    @Test
    public void decompressGzip() throws Exception {
        File tmp = temporaryFolder.newFile();
        CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();

        LOGGER.info("Compressed file: {}, size: {}", compressedFile, compressedFile.length());


        try (BufferedReader reader = new BufferedReader(
                     new InputStreamReader(new GZIPInputStream(new FileInputStream(compressedFile))));
             FileChannel writable = new FileOutputStream(tmp).getChannel()
        ) {
            String s;
            while ((s = reader.readLine()) != null) {
                s += "\n";
                writable.write(encoder.encode(CharBuffer.wrap(s)));
            }
        }

        LOGGER.info("Uncompressed file: {}, size: {}", tmp, tmp.length());

        assertEquals(TextFile.read(asciiSample.getAbsolutePath()), TextFile.read(tmp.getAbsolutePath()));
    }
}

