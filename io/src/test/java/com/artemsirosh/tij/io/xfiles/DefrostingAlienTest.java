package com.artemsirosh.tij.io.xfiles;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created on 21 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class DefrostingAlienTest {

    @Test
    public void twanAlien() throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("x.file"))) {
            Object mystery = inputStream.readObject();
            System.out.println(mystery.getClass());
        }
    }
}
