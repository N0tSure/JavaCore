package ekkel.book.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class DirectoriesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoriesTest.class);

    @Test
    public void directoryTest() throws Exception {
        LOGGER.info(Directory.walk(".", "T.*\\.class").toString());
    }
}
