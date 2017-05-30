package ekkel.book.io;

import ekkel.book.util.TextFile;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class TextFileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextFileTest.class);
    private static final String ASCII_SAMPLE = "src/main/resources/asci.sample";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder(new File("src/main/resources"));

    @Test
    public void fileWritingTest() throws Exception {
        File tmp_folder = temporaryFolder.newFolder();
        String expected = TextFile.read(ASCII_SAMPLE);
        LOGGER.info("Expected file length: {}", expected.length());

        String fileName = tmp_folder.getAbsolutePath() + "/" + "tmp";
        TextFile.write(fileName, expected);

        String actual = TextFile.read(fileName);
        LOGGER.info("Actual file length: {}", actual.length());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sortingStringsTest() throws Exception {
        TreeSet<String> uniqueWords = new TreeSet<>(new TextFile(ASCII_SAMPLE, "\\W+"));
        LOGGER.info("Unique words from 'a': {}", uniqueWords.tailSet("a"));
    }

    @Test
    public void symbolCounter() throws Exception {
        Map<Character, Integer> result = new HashMap<>();
        char[] source = TextFile.read(ASCII_SAMPLE).toCharArray();

        IntStream.range(0, source.length).mapToObj(i -> source[i]).forEach(character -> {
            if (result.containsKey(character))
                result.put(character, result.get(character) + 1);
            else
                result.put(character, 1);

        });

        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            LOGGER.info("{} : {}", Character.getName(entry.getKey()), entry.getValue());
        }
    }
}
