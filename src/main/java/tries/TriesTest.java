package tries;

import ekkel.book.util.TextFile;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * <p>
 * Created on 12.07.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class TriesTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TriesTest.class);
    private static char[] alphabet;

    @BeforeClass
    public static void setUp() throws Exception {
        alphabet = new char[92 - 65];
        for (int i = 0; i < alphabet.length - 1; i++) {
            alphabet[i] = (char) (i + 65);
        }

        alphabet[alphabet.length - 1] = '\'';
        LOGGER.info("Alphabet: {}", Arrays.toString(alphabet));
        LOGGER.info("Alphabet: {}", (int) alphabet[alphabet.length - 1]);
    }

    @Test
    public void indexFuncTest() throws Exception {
        String indexes = "";

        for (char c : alphabet) {
            int index;

            if (c != '\'')
                index = c - 65;
            else
                index = 26;

            assertTrue(index < 27);
            assertTrue(index >= 0);
            indexes += " " + index;
        }

        LOGGER.info("Obtained indexes: {}", indexes);
    }

    @Test
    public void growingTest() throws Exception {
        Node node = new Node(false);
        node.addChild("ABC".toCharArray(), 0, 3);

    }

    @Test
    public void searchingTest() throws Exception {
        String word = "Obtained".toUpperCase();
        Node node = new Node(false);
        node.addChild(word.toCharArray(), 0, word.length());

        String anotherWord = "Constructor".toUpperCase();

        for (int i = 0; i < 100; i++) {
            assertFalse("Longer word", node.isPresent(anotherWord.toCharArray(), 0, anotherWord.length()));
            assertTrue("Target word not found", node.isPresent(word.toCharArray(), 0, word.length()));
            assertFalse("Shorter word", node.isPresent("ABC".toCharArray(), 0, 3));
        }

    }

    @Test
    public void clearingTest() throws Exception {
        List<String> words =
                new TextFile("D:\\train\\Core\\core-training\\src\\main\\resources\\asci.sample", "\\W+");
        Node root = new Node(false);

        for (String word : words) {
            root.addChild(word.toUpperCase().toCharArray(), 0, word.length());
        }

        for (String word : words) {
            System.out.println(word);
            assertTrue(root.isPresent(word.toUpperCase().toCharArray(), 0, word.length()));
        }

    }
}
