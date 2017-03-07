package shield.book.test02;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * This is solution of problem
 *
 */
public class QuickSearcher implements CharacterSearcher {

    @Override
    public int countCharacters(char keyChar, String target) throws Exception {
        final int[] counter = {0};
        Files.lines(Paths.get(target)).forEach(s -> {
            for(char c :s.toCharArray()) {
                if (c==keyChar) counter[0]++;
            }
        });
        return counter[0];
    }

}
