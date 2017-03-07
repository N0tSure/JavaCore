package ekkel.book.strings;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Created by cresh on 21.07.16.
 */
public class TestRegularExpression {
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("Wrong syntax.\njava TestRegularExpression charSequence regex");
            System.exit(0);
        }

        System.out.println("Input: \"" + args[0] + "\"");
        for (String regex : args) {
            System.out.println("Regex: " + regex);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(args[0]);
            while (matcher.find()) {
                System.out.printf("Match \"%1$s\" at positions %2$d-%3$d\n",
                        matcher.group(),matcher.start(),(matcher.end()-1));
            }
        }
    }
}
