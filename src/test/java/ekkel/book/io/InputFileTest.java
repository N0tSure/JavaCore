package ekkel.book.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created on 26.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class InputFileTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(InputFileTest.class);

    @Test
    public void reverseStrings() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/asci.sample"));
        Deque<String> deque = new LinkedList<>();
        String s;

        while ((s = reader.readLine()) != null)
            deque.addFirst(s);

        while (deque.size() != 0)
            System.out.println(deque.removeFirst().toUpperCase());

        reader.close();
    }

    @Test
    public void searchWordInFile() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/asci.sample"));
        Deque<String> deque = new LinkedList<>();
        String s;

        int line_count = 0;
        while ((s = reader.readLine()) != null) {
            line_count++;
            if (s.contains("Muse"))
                deque.add(s);
        }


        for (String line : deque)
            LOGGER.info("{} : {}", line_count++, line);

        reader.close();

    }

    @Test
    public void memoryInput() throws Exception {
        StringReader reader = new StringReader(FileToString.read("./src/main/resources/asci.sample"));
        int c;
        while ((c = reader.read()) != -1)
            System.out.print((char) c);

        reader.close();
    }

    @Test
    public void formattedMemoryInput() throws Exception {
        DataInputStream inputStream = new DataInputStream(
                new ByteArrayInputStream(
                        FileToString.read("./src/main/resources/asci.sample").getBytes()
                )
        );

        while (inputStream.available() > 0)
            System.out.print(inputStream.readChar());

        inputStream.close();
    }


}
