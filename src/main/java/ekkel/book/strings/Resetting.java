package ekkel.book.strings;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 26.07.16.
 */
public class Resetting {
    public static void main(String[] args) throws IOException {
        Matcher matcher = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (matcher.find()) System.out.print(matcher.group() + " ");
        System.out.println();
        matcher.reset("fix the rig with rags");
        while (matcher.find()) System.out.print(matcher.group() + " ");
    }
}
