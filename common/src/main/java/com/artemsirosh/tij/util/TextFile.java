package com.artemsirosh.tij.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cresh on 12.03.17.
 */
public class TextFile extends ArrayList<String> {

    public TextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Regular expression split() often leaves an empty
        // String at the first position:
        if (get(0).equals(""))
            remove(0);
    }

    public TextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try (PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile())) {
            for (String item : this)
                out.println(item);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(String fileName, String text) {
        try (PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile())) {
            out.print(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()))) {

            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
