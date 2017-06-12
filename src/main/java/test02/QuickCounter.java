package test02;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>
 *     Searching character using streams, which created
 *     directly from file name
 * </p>
 * <p>
 *     <em>Note:</em> Have some issues with file closing
 * </p>
 *
 */
public class QuickCounter implements CharacterCounter {

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
