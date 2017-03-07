package ekkel.book.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cresh on 25.07.16.
 */
public class StartEnd {
    public static String input = "As long as there is injustice, whenever a\n" +
            "Targathian baby cries out, wherever a distress\nsignal sounds among stars...\n" +
            "We'll be there.\nThis fine ship, and this fine crew...\n" +
            "Never give up! Never surrender!";

    private static class Display {
        private boolean isPrinted = false;
        private String regex;
        Display(String regex) {
            this.regex = regex;
        }
        void display(String msg) {
            if (!isPrinted) {
                System.out.println(regex);
                isPrinted = true;
            }
            System.out.println(msg);
        }
    }
    static void examine(String input, String regex) {
        Display display = new Display(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find())
            display.display(String.format("Found: %1$s\n starts: %2$d, ends: %3$d",
                    matcher.group(),matcher.start(),matcher.end()));

            if (matcher.lookingAt())
                display.display(String.format("Starts with: %1$d, end with: %2$d",
                        matcher.start(),matcher.end()));


            if (matcher.matches())
                display.display(String.format("All input matches: start: %1$d, end: %2$d",
                        matcher.start(),matcher.end()));

    }
    public static void main(String[] args) {
        for (String input : Groups.POEM.split("\n")) {
            System.out.println(input);
            for (String regex : new String[]{"\\w*ere\\w*", "\\w*ware", "T\\w+", "Never.*?!"})
                examine(input, regex);
        }
    }
}
