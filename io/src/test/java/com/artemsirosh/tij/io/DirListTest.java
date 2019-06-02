package com.artemsirosh.tij.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class DirListTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirListTest.class);

    @Test
    public void sortedDirListTest() throws Exception {
        SortedDirectoryList directoryList =
                new SortedDirectoryList(".");


        for (File file : directoryList.list("\\..*")) {
            LOGGER.info("{} exists: {}", file.getName(), (file.exists()) ? "yes" : "no");
            LOGGER.info("{} directory: {}\n", file.getName(), (file.isDirectory()) ? "yes" : "no");
        }
    }

    @Test
    public void fileSizeCounterTest() throws Exception {
        FileSizeCounter counter = new FileSizeCounter(".");

        LOGGER.info("{}", counter);
    }
}
