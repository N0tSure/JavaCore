package ekkel.book.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;

import static org.junit.Assert.assertEquals;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class RandomAccessFileUsageTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(RandomAccessFileUsageTest.class);

    private static final double FACTOR = 1.414;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(new File("src/main/resources/"));

    private File data;

    @Before
    public void setUp() throws Exception {
        this.data = temporaryFolder.newFile();

        RandomAccessFile writer = new RandomAccessFile(data, "rw");
        for (int i = 0; i < 7; i++) {
            writer.writeDouble(FACTOR * i);
        }

        writer.writeUTF("The end of file");
        writer.close();

        LOGGER.info("Created {}, length: {}", this.data, this.data.length());
    }

    @Test
    public void usageTest() throws Exception {

        RandomAccessFile reader = new RandomAccessFile(data, "r");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            double actual = reader.readDouble();
            assertEquals(FACTOR * i, actual, 0.001);

            builder.append(actual).append(' ');
        }

        LOGGER.info("{}", builder);
        LOGGER.info("{}", reader.readUTF());

    }
}
