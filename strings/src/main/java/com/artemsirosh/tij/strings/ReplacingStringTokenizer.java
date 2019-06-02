package com.artemsirosh.tij.strings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by cresh on 29.07.16.
 */
public class ReplacingStringTokenizer {
    public static void main(String[] args) {
        String input = "But I'm not dead yet! I feel happy!";
        StringTokenizer tokenizer = new StringTokenizer(input);
        while (tokenizer.hasMoreElements()) System.out.printf("%s \n",tokenizer.nextToken());
        System.out.println();
        System.out.println(Arrays.toString(input.split(" ")));
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) System.out.print(scanner.next() + " ");
    }
}
