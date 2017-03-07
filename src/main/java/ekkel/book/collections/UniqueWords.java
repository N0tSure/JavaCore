package ekkel.book.collections;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by NotSure on 03.03.16.
 */
public class UniqueWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        Set<String> words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        Set<String> letters = new HashSet<String>();
        Collections.addAll(letters,"A E I O U Y".split(" "));
        String line = "";
        int inWord, overAll;


        try {
            reader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException exc) {
            System.out.println("File not found.");
        } catch (IndexOutOfBoundsException exc) {
            System.out.println("Filename wasn't defined.");
        }

        if (reader!=null) {
            while (true) {
                line = reader.readLine();
                if (line==null) break;
                for (String s : line.split("\\W+")) {
                    try {
                        words.add(s);
                    } catch (Exception exc) {
                        System.out.println("Error: " + exc.getMessage());
                    }
                }
            }
        }
        overAll=0;
        for (String word : words) {
            inWord=0;
            for (Character letter : word.toCharArray()) {
                if (letters.contains(Character.toString(Character.toUpperCase(letter)))) {
                    inWord++;
                    overAll++;
                } else if (letters.contains(letter.toString())) {
                    inWord++;
                    overAll++;
                }
            }
            System.out.printf("%-10s : %d\n",word,inWord);
        }
        System.out.println("All letters: " + overAll);
    }
}
