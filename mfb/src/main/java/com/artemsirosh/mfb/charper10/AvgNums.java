package com.artemsirosh.mfb.charper10;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by cresh on 07.02.16.
 */
public class AvgNums {
    public static void main(String[] args) throws IOException {
        Console cons = System.console();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        char[] pwd;

        while (line==null) {
            line=reader.readLine();
        }

        System.out.println("Enter password: ");
        pwd=cons.readPassword();


        String[] strings = line.split(" ");

        for (String string : strings) {
            System.out.println(string);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pwd);
        line=stringBuilder.toString();
        System.out.println(line);
    }
}
