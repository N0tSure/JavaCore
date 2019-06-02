package com.artemsirosh.tij.test02;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>
 *     Standard and obvious realization of CharacterCounter,
 *     used how reference
 * </p>
 */
public class ObviousCounter implements CharacterCounter {
    @Override
    public int countCharacters(char keyChar, String path) {
        int counter = 0;
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext())
                builder.append(scanner.next());
            char[] targetChars = builder.toString().toCharArray();
            for (int i = 0; i < targetChars.length; i++) {
                if (targetChars[i]==keyChar) counter++;
            }
        } catch (IOException exc) {
            System.err.println(exc.getMessage());
        }
        return counter;
    }
}
