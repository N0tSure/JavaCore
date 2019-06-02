package com.artemsirosh.tij.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created on 26.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class OutputFileTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(OutputFileTest.class);

    @Test
    public void countLineInFileTest() throws Exception {
        BufferedReader reader = new BufferedReader(
                new StringReader(FileToString.read("./src/main/resources/asci.sample"))
        );

        PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter("./src/main/resources/counted.txt"))
        );

        int lineCount = 1;
        String s;
        while ((s = reader.readLine()) != null)
            writer.printf("%03d : %s\n", lineCount++, s);

        reader.close();
        writer.close();

        File file = new File("./src/main/resources/counted.txt");
        LOGGER.info("Created:\n{}", FileToString.read(file));
        file.delete();
    }
}
