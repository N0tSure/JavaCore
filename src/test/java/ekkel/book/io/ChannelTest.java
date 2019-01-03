package ekkel.book.io;

import ekkel.book.util.OSExecute;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 03 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class ChannelTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelTest.class);
    private static final int BUFFER_SIZE = 1024;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void getChannel() throws Exception {
        File tmp = temporaryFolder.newFile();

        FileChannel channel = new FileOutputStream(tmp).getChannel();
        channel.write(ByteBuffer.wrap("foo".getBytes()));
        channel.close();

        channel = new RandomAccessFile(tmp, "rw").getChannel();
        channel.position(channel.size());
        channel.write(ByteBuffer.wrap("bar".getBytes()));
        channel.close();

        channel = new FileInputStream(tmp).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        channel.read(buffer);
        buffer.flip();

        StringBuilder builder = new StringBuilder();
        while (buffer.hasRemaining())
            builder.append((char) buffer.get());

        LOGGER.info("Read: {}", builder);

    }

    @Test
    public void channelCopy() throws Exception {
        File original = new File("/home/train/Core/src/main/resources/smiley.bmp");
        File copy = temporaryFolder.newFile();

        FileChannel
                input = new FileInputStream(original).getChannel(),
                output = new FileOutputStream(copy).getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        while (input.read(buffer) != -1) {
            buffer.flip();
            output.write(buffer);
            buffer.clear();
        }

        String command = String.format("diff %s %s", original.getAbsolutePath(), copy.getAbsolutePath());
        LOGGER.info("Command: {}", command);

        OSExecute.command(command);
    }

    @Test
    public void simplerCopyChannel() throws Exception {
        File original = new File("/home/train/Core/src/main/resources/smiley.bmp");
        File copy = temporaryFolder.newFile();

        FileChannel
                input = new FileInputStream(original).getChannel(),
                output = new FileOutputStream(copy).getChannel();

        output.transferFrom(input, 0, input.size());

        OSExecute.command(String.format("diff %s %s", original.getAbsolutePath(), copy.getAbsolutePath()));
    }
}
