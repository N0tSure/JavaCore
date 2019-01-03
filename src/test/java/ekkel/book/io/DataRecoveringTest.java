package ekkel.book.io;

import ekkel.book.util.ContainerToString;
import ekkel.book.util.TextFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.NoSuchFileException;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class DataRecoveringTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataRecoveringTest.class);

    private File data;

    @Before
    public void setUp() throws Exception {
        this.data = new File("src/main/resources/data.txt");
        if (!data.createNewFile())
            throw new NoSuchFileException("src/main/resources/");

        LOGGER.info("Created {}", this.data);
    }

    @After
    public void tearDown() throws Exception {
        if (this.data != null)
            LOGGER.info("Delete {}: {}", this.data, this.data.delete());
    }

    @Test
    public void recoveringTest() throws Exception {
        DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(this.data)));

        // Data(Output-Input)Stream types
        byte byteExpected = 65;
        char characterExpected = 't';
        short shortExpected = 1703;
        boolean boolExpected = true;
        int intExpected = 3227246;
        float floatExpected = 1.4178f;
        long longExpected = 795238713;
        double doubleExpected = 3.14159;
        String stringExpected = "This is pi";

        String bytesExpected = "Square";
        String charsExpected = "Pirates";

        output.writeByte(byteExpected);
        output.writeChar(characterExpected);
        output.writeShort(shortExpected);
        output.writeBoolean(boolExpected);
        output.writeInt(intExpected);
        output.writeFloat(floatExpected);
        output.writeLong(longExpected);
        output.writeDouble(doubleExpected);
        output.writeUTF(stringExpected);

        output.writeBytes(bytesExpected);

        output.writeChars(charsExpected);

        output.close();

        DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(data)));

        assertEquals(byteExpected, input.readByte());
        assertEquals(characterExpected, input.readChar());
        assertEquals(shortExpected, input.readShort());
        assertTrue(input.readBoolean());
        assertEquals(intExpected, input.readInt());
        assertEquals(floatExpected, input.readFloat(), 0.0);
        assertEquals(longExpected, input.readLong());
        assertEquals(doubleExpected, input.readDouble(), 0.0);
        assertEquals(stringExpected, input.readUTF());

        byte[] actualBytes = new byte[bytesExpected.length()];
        input.read(actualBytes);

        byte[] expectedBytes = bytesExpected.getBytes();
        assertArrayEquals(expectedBytes, actualBytes);


        char[] actualChars = new char[charsExpected.length()];
        for (int i = 0; i < actualChars.length; i++) {
            actualChars[i] = input.readChar();
        }

        char[] expectedChars = charsExpected.toCharArray();
        assertArrayEquals(expectedChars, actualChars);

        input.close();

        LOGGER.info("{} :\n{}", data, ContainerToString.format(new TextFile(data.getAbsolutePath())));

    }
}
