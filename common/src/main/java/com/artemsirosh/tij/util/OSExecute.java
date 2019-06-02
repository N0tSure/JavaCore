package com.artemsirosh.tij.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class OSExecute {

    public static void command(String command) {
        List<String> errors = new ArrayList<>();
        try {

            Process process = new ProcessBuilder(command.split(" ")).start();
            process.waitFor();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String s;
                while ((s = reader.readLine()) != null)
                    System.out.println(s);
            }


            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String s;
                while ((s = errorReader.readLine()) != null)
                    errors.add(s);
            }

        } catch (IOException | InterruptedException exc ) {
            if (!errors.isEmpty())
                throw new OSExecuteException(exc, errors);

            throw new RuntimeException(exc);
        }

        if (!errors.isEmpty())
            throw new OSExecuteException(errors);
    }
}
