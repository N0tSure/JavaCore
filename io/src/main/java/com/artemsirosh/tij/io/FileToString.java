package com.artemsirosh.tij.io;

import java.io.*;

/**
 * Created on 26.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class FileToString {

    static String read(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.isDirectory())
            return "";

        return read(file);
    }

    static String read(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            StringBuilder builder = new StringBuilder();
            while ((s = reader.readLine()) != null)
                builder.append(s).append('\n');

            return builder.toString();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
}
