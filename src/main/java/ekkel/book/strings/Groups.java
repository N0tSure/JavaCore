package ekkel.book.strings;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 21.07.16.
 */
public class Groups {
    public static final String POEM = "Twas brillig, the slithy toves\nDid gyre and gimble in the wabe.\n" +
            "All mimsy were the borogoves,\n" + "And mome raths outgrabe.\n\nBeware the Jabberwock, my son,\n" +
            "The jaws that bite, the claws that catch.\nBeware the Jubjub bird, and shun\nThe frumious Bendersnatch.";
    public static void main(String[] args) {
//        Matcher matcher = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(POEM);
        Matcher matcher = Pattern.compile("(?m)\\s\\p{Lower}\\w+").matcher(POEM);
        Set<String> words = new HashSet<>();
        String s;
        while (matcher.find()) {
            s = matcher.group().trim();
            words.add(s);
        }
        System.out.println(words);
    }
}
