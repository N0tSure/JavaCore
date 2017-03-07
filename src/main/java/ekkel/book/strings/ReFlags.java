package ekkel.book.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 25.07.16.
 */
public class ReFlags {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^java",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("java has regex\nJava has regex\n" +
                "JAVA has pretty good regular expressions\nRegular expressions are in Java");
        while (matcher.find()) System.out.println(matcher.toMatchResult().group());
    }
}
