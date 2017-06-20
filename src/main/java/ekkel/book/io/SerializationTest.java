package ekkel.book.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.junit.Assert.assertEquals;

/**
 * Created on 18 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class SerializationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializationTest.class);
    private static final File RESOURCES = new File("/home/train/Core/src/main/resources");
    private File dataFile = new File("worm.dat");
    private byte[] storage;
    private Worm original;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void wormSerialization() throws Exception {

        original = new Worm(6, 'a');
        LOGGER.info("Original: {}", original);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {

            outputStream.writeObject("Worm storage");
            outputStream.writeObject(original);
            storage = byteArrayOutputStream.toByteArray();
        }

        try (FileChannel channel = new FileOutputStream(dataFile).getChannel()) {
            ByteBuffer buffer = ByteBuffer.wrap(storage);
            channel.write(buffer);
        }

        LOGGER.info("Size of in memory storage: {}", storage.length);
    }

    @After
    public void tearDown() throws Exception {
        dataFile.delete();
    }

    @Test
    public void wormDeserialization() throws Exception {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dataFile))) {
            String s = (String) inputStream.readObject();
            Worm head = (Worm) inputStream.readObject();

            assertEquals(original, head);
            LOGGER.info("{} : {}", s, head);
        }
    }

    @Test
    public void byteArrayManipulations() throws Exception {

        try (ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(storage))) {

            String s = (String) inputStream.readObject();
            Worm head = (Worm) inputStream.readObject();

            assertEquals(original, head);
            LOGGER.info("{} : {}", s, head);
        }
    }

    @Test
    public void freezeAlien() throws Exception {
        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream("x.file"))) {
            Alien quellek = new Alien();
            output.writeObject(quellek);
        }
    }
}
