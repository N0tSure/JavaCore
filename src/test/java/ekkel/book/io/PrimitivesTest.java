package ekkel.book.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;

/**
 * Created on 04 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class PrimitivesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrimitivesTest.class);
    private static final int BUFFER_SIZE = 1024;

    @Test
    public void getData() throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        // Allocation automatically zeroes the ByteBuffer:
        int i = 0;
        while(i++ < buffer.limit())
            if(buffer.get() != 0)
                LOGGER.info("nonzero");
        LOGGER.info("i = " + i);
        buffer.rewind();

        // Store and read a char array:
        buffer.asCharBuffer().put("Howdy!");
        char c;
        StringBuilder s = new StringBuilder();
        while((c = buffer.getChar()) != 0)
            s.append(c).append(" ");
        LOGGER.info("String: {}", s);
        buffer.rewind();

        // Store and read a short:
        buffer.asShortBuffer().put((short) 471142);
        LOGGER.info("Short: {}", buffer.getShort());
        buffer.rewind();

        // Store and read an int:
        buffer.asIntBuffer().put(99471142);
        LOGGER.info("Integer: {}", buffer.getInt());
        buffer.rewind();

        // Store and read a long:
        buffer.asLongBuffer().put(99471142);
        LOGGER.info("Long: {}", buffer.getLong());
        buffer.rewind();

        // Store and read a float:
        buffer.asFloatBuffer().put(99471142);
        LOGGER.info("Float: {}", buffer.getFloat());
        buffer.rewind();

        // Store and read a double:
        buffer.asDoubleBuffer().put(99471142);
        LOGGER.info("Double: {}", buffer.getDouble());
        buffer.rewind();
    }

    @Test
    public void integerBuffers() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
        IntBuffer buffer = byteBuffer.asIntBuffer();

        buffer.put(new int[]{11, 42, 47, 99, 143, 811, 1016});
        LOGGER.info("position 3: {}", buffer.get(3));
        buffer.put(3, 1811);
        buffer.flip();

        StringBuilder builder = new StringBuilder();
        while (buffer.hasRemaining())
            builder.append(buffer.get()).append(' ');

        LOGGER.info("Putted: {}", builder);
    }

    @Test
    public void usingBuffers() throws Exception {
        char[] data = "foobar".toCharArray();
        ByteBuffer bb = ByteBuffer.allocate(data.length * 2);
        CharBuffer buffer = bb.asCharBuffer();
        buffer.put(data);
        LOGGER.info("Original: {}", buffer.rewind());
        symmetricScramble(buffer);
        LOGGER.info("After 1 scramble: {}", buffer.rewind());
        symmetricScramble(buffer);
        LOGGER.info("After 2 scramble: {}", buffer.rewind());
    }

    private void symmetricScramble(CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            buffer.mark();
            char c1 = buffer.get();
            char c2 = buffer.get();
            buffer.reset();
            buffer.put(c2).put(c1);

        }
    }
}
