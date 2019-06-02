package com.artemsirosh.tij.strings;

/**
 * Created by cresh on 18.07.16.
 */
public class Replacing {
    public static void main(String[] args) {
        System.out.println(Splitting.knights.replaceFirst("f\\w+","located"));
        System.out.println(Splitting.knights.replaceAll("shrubbery|tree|herring","banana"));
        System.out.println(Splitting.knights.replaceAll("[aeiou]","_"));
    }
}
