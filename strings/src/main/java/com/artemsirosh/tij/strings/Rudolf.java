package com.artemsirosh.tij.strings;

/**
 * Created by cresh on 19.07.16.
 */
public class Rudolf {
    public static void main(String[] args) {
        for (String pattern : new String[]{"Rudolf","[rR]udolf","[rR][aeiou][a-z]ol.*","R.*"})
            System.out.println(Rudolf.class.getSimpleName().matches(pattern));
    }
}
