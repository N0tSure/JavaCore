package com.artemsirosh.mfb.charper10;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by cresh on 07.02.16.
 */
public class KtoD {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        FileWriter writer;
        System.out.println("Пpизнaк конца ввода - строка \'stop\'");

        try {
            writer = new FileWriter("text.log",true);
            do {
                System.out.print(": ");
                line = reader.readLine();
                if (line.compareTo("stop")==0) break;

                line += "\r\n";
                writer.write(line);
            } while (line.compareTo("stop")!=0);
            writer.close();
        } catch (IOException exc) {
            System.out.println("An Error: " + exc);
        }
    }
}
