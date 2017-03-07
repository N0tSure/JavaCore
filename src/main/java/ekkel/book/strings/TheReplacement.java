package ekkel.book.strings;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*!
 * Created by cresh on 25.07.16.
 * Here's a block of text to use as input to
 * the regular expression matcher. Note that we'll
 * first extract the block of text by looking for
 * the special delimiters, then process the extracted
 * block.
 !*/
public class TheReplacement {
    public static String loadTextFile(String path) throws IOException {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        File[] files = null;
        Scanner scanner;
        if (!file.exists()) return "";
        if (file.isDirectory()) files = file.listFiles();
        else {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine());
                result.append("\n");
            }
        }

        if (files!=null) {
            for (File f : files) {
               scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    result.append(scanner.nextLine());
                    result.append("\n");
                }
            }
        }

        return new String(result);
    }



    public static void main(String[] args) throws IOException {
        String input = loadTextFile("./src/ekkel/book/strings/");
        Matcher matcher = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL).matcher(input);
        if (matcher.find()) input = matcher.group(1);
        input = input.replaceAll(" {2,}"," ");
        input = input.replaceAll("(?m)^ ","");
        input = input.replaceFirst("[aeiou]","VOWEL1");
        StringBuffer buffer = new StringBuffer(input);
        matcher = Pattern.compile("[aeiou]").matcher(input);
        while (matcher.find())
            matcher.appendReplacement(buffer,matcher.group().toUpperCase());
        buffer = matcher.appendTail(buffer);
        System.out.println(buffer);
    }
}
