package com.artemsirosh.tij.strings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by cresh on 29.07.16.
 */
public class ClazzFinder {
    private static List<String> getComments(String path) {
        String input = "";
        List<String> result = new ArrayList<>();
        try {
            input = TheReplacement.loadTextFile(path);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        Matcher matcher = Pattern.compile("/\\*([^*]|[\\r\\n]|(\\*([^/]|[\\r\\n])))*\\*/").matcher(input);

        while (matcher.find()) result.add(matcher.group());
        return result;
    }

    public static void main(String[] args) {
        for (String s : getComments("./src/ekkel/book/strings")) System.out.println(s);
    }
}
