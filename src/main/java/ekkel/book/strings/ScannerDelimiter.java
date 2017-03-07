package ekkel.book.strings;

import java.util.Scanner;

/**
 * Created by cresh on 29.07.16.
 */
public class ScannerDelimiter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12, 42, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt()) System.out.println(scanner.nextInt());
    }
}
