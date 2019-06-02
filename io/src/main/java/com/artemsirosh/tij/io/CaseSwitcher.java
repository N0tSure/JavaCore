package com.artemsirosh.tij.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class CaseSwitcher {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s;
            while ((s = reader.readLine()) != null)
                System.out.println(s.toUpperCase());
        } catch (IOException exc) {
            System.err.println("Wrong try: " + exc);
        }
    }
}
