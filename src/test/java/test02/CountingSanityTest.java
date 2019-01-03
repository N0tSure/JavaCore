package test02;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.CharMatcher.is;

/* This test was wrote for compare realization
 * of CharacterCounter and measure execution time */
public class CountingSanityTest {

    private final static String ASCII_SAMPLE = "/home/train/Core/src/main/resources/asci.sample";
    private final static String NOT_ASCII_SAMPLE = "/home/train/Core/src/main/resources/n_asci.sample";
    private final static String LARGE_SAMPLE = "/home/train/Core/src/main/resources/large.sample";

    private static final Logger LOGGER = LoggerFactory.getLogger(CountingSanityTest.class);
    private static Map<Character, Integer> reference;

    @BeforeClass
    public static void setObviousAsReference() throws Exception {
        CharacterCounter searcher = new ObviousCounter();
        char[] soughtCharacters = "onvMus는절에수ступТвон".toCharArray();
        reference = new HashMap<>();

        for (char character : soughtCharacters) {
            reference.put(character, searcher.countCharacters(character, NOT_ASCII_SAMPLE));
        }
    }

    @Test
    public void quickCounterTest() throws Exception {
        CharacterCounter counter = new QuickCounter();

        for (Character character : reference.keySet()) {
            int countedResult = counter.countCharacters(character, NOT_ASCII_SAMPLE);
            Assert.assertEquals(countedResult, (int) reference.get(character));
        }
    }

    @Test
    public void nonWrappingCounterTest() throws Exception {
        CharacterCounter counter = new NonWrappingCounter();

        for (Character character : reference.keySet()) {
            int countedResult = counter.countCharacters(character, NOT_ASCII_SAMPLE);
            Assert.assertEquals(countedResult, (int) reference.get(character));
        }
    }

    @Test
    public void fileMapTest() throws Exception {
        CharacterCounter counter = new FileMappingCounter();

        for (Character character : reference.keySet()) {
            int countedResult = counter.countCharacters(character, NOT_ASCII_SAMPLE);
            Assert.assertEquals(countedResult, (int) reference.get(character));
        }
    }

    /* There 2 files for test:
    * sample.txt - small sample file
    * data.txt - large sample file*/
    @Test
    public void countingTimeMeasureTest() throws Exception {
        // input samples
        char[] soughtCharacters = "onvMus는절에수ступТвон".toCharArray();
        String targetPath = "/home/train/Core/src/main/resources/asci.sample";

        CharacterCounter obviousSearcher = new ObviousCounter();
        CharacterCounter quick = new QuickCounter();
        runModule(obviousSearcher, soughtCharacters, targetPath, 5);
        runModule(quick, soughtCharacters, targetPath, 5);
    }

    /*Measure exec time and print results
    * @searcher CharacterCounter that examine
    * @path path to sample file
    * @n number of attempts*/
    private void runModule(CharacterCounter searcher, char[] keys, String path, int n) throws Exception {
        System.out.printf("Measurement of execution time for %s\n", searcher.getClass().getSimpleName());

        long before = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            for (char key : keys) {
                int counted = searcher.countCharacters(key, path);
                System.out.printf("For \'%c\': %d entries\n", key, counted);
            }
        }
        long after = System.currentTimeMillis();

        System.out.printf(
                "Total execution time for %d attempt of %d characters: %d\n",
                n, keys.length, after - before);
    }

}
