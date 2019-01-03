package tries;

import ekkel.book.util.TextFile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

/**
 * <p>
 * Created on 13.07.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class TrieSetTest {

    private static Set<String> dictionary;

    @BeforeClass
    public static void creation() {
        TrieSetTest.dictionary = new TrieSet();

        for (String s : new TextFile("D:\\train\\Core\\core-training\\samples\\dictionaries\\large", "\n")) {
            dictionary.add(s);
        }
    }

    @Test
    public void searchingTest() {
        for (String s : new TextFile("D:\\train\\Core\\core-training\\samples\\texts\\xueqin1.txt", "\\W+")) {

            for (int i = 0; i < s.length(); i++) {

                if (!Character.isAlphabetic(s.charAt(i)))
                    continue;
            }

            try {
                if (!dictionary.contains(s))
                    System.out.println(s);
            } catch (Exception exc) {
                System.err.println("Wrong word! " + s);
            }
        }
    }

}
