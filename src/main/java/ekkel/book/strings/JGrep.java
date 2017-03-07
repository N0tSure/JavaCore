package ekkel.book.strings;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 26.07.16.
 */
public class JGrep {
    public static void main(String[] args) throws IOException {
        if (args.length<2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }

        Pattern p = Pattern.compile(args[1]);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for(String line : TheReplacement.loadTextFile(args[0]).split("\n")) {
            m.reset(line);
            while(m.find())
                System.out.printf("%1$d: %2$s: %3$d\n",index++,m.group(),m.start());
        }
    }
}
