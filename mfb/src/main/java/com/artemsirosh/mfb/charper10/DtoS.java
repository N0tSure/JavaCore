package com.artemsirosh.mfb.charper10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by cresh on 07.02.16.
 */
public class DtoS {
    public static void main(String[] args) throws IOException {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/cresh/Документы/100 книг которые нужно почитать перед смертью.txt"));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException exc) {
            System.out.println(exc);
        }

    }
}
