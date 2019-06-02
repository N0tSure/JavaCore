package com.artemsirosh.tij.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Iterator;
import java.util.Map;

/**
 * Created on 04 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class EncodingTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncodingTest.class);
    private static final int BUFFER_SIZE = 1024;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void bufferToTextTest() throws Exception {
        File tmp = temporaryFolder.newFile();

        FileChannel channel = new FileOutputStream(tmp).getChannel();
        channel.write(ByteBuffer.wrap("Foo bar".getBytes()));
        channel.close();

        channel = new FileInputStream(tmp).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BUFFER_SIZE);
        channel.read(buff);
        buff.flip();

        // Doesn't work:
        LOGGER.info("Without decoding {}", buff.asCharBuffer());

        // Decode using this system's default Charset:
        buff.rewind();
        String encoding = System.getProperty("file.encoding");
        LOGGER.info("Decoded using {}: {}", encoding, Charset.forName(encoding).decode(buff));

        // Or, we could encode with something that will print:
        channel = new FileOutputStream(tmp).getChannel();
        channel.write(ByteBuffer.wrap("Baz qux".getBytes("UTF-16BE")));
        channel.close();

        // Now try reading again:
        channel = new FileInputStream(tmp).getChannel();
        buff.clear();
        channel.read(buff);
        buff.flip();
        LOGGER.info("Encoded UTF-16BE: {}", buff.asCharBuffer());

        // Use a CharBuffer to write through:
        channel = new FileOutputStream(tmp).getChannel();
        buff = ByteBuffer.allocate(24); // More than needed
        buff.asCharBuffer().put("Quux");
        channel.write(buff);
        channel.close();

        // Read and display:
        channel = new FileInputStream(tmp).getChannel();
        buff.clear();
        channel.read(buff);
        buff.flip();
        LOGGER.info("Written through: {}", buff.asCharBuffer());

    }

    @Test
    public void availableCharSets() throws Exception {
        StringBuilder builder = new StringBuilder();
        Map<String, Charset> availableCharsets = Charset.availableCharsets();

        for (Map.Entry<String, Charset> entry : availableCharsets.entrySet()) {
            builder.append(entry.getKey());
            Charset charset = entry.getValue();

            if (!charset.aliases().isEmpty()) {
                builder.append(": ");

                for (String alias : charset.aliases()) {
                    builder.append(alias).append(", ");
                }

                builder.delete(builder.length() - 2, builder.length());
            }

            builder.append(";\n");
        }

        LOGGER.info("\n{}", builder);
    }

    @Test
    public void detectUndecidableCharacter() throws Exception {
        ByteBuffer buffer;
        CharBuffer result;

        buffer = ByteBuffer.wrap("Вот такие дела".getBytes("cp1251"));

        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
        buffer.rewind();
        result = decoder.decode(buffer);

        while (result.hasRemaining()) {
            char c = result.get();
            LOGGER.info("Readable: {}, {}", Character.isValidCodePoint(c), c);
        }
    }
}
