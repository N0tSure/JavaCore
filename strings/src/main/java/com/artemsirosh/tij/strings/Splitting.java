package com.artemsirosh.tij.strings;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by cresh on 18.07.16.
 */
public class Splitting {
    public static String knights = "Then, when you have found the shrubbery, you must cut down " +
            "the mightiest tree in the forest... with.. a herring!";

    public static String split(String regex) {
        return Arrays.toString(knights.split(regex));
    }

    public static String patternSplit(String regex) {
        return Arrays.toString(Pattern.compile(regex).split(knights,3));
    }

    public static void main(String[] args) {
        System.out.println(split(" "));
        System.out.println(split("\\W+"));
        System.out.println(split("n\\W+"));
        System.out.println(split("the|you"));

        System.out.println("-------------");
        System.out.println(patternSplit(" "));
        System.out.println(patternSplit("\\W+"));
        System.out.println(patternSplit("n\\W+"));
        System.out.println(patternSplit("the|you"));
    }
}
