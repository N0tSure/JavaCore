package com.artemsirosh.tij.strings;

/**
 * Created by cresh on 08.02.16.
 */
public class SimpleFormat {
    public static void main(String[] args) {
        int x = 5;
        double pi = 3.1415;

        System.out.println("Row 1: [" + x + " " + pi + "]");

        System.out.printf("Row 1: [%d %.4f]\n", x,pi);

        System.out.format("Row 1: [%d %f]\n", x,pi);
    }
}
