package com.artemsirosh.tij.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 21 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class PermanentStorage {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermanentStorage.class);

    @Test
    public void deepObjectStorage() throws Exception {

        House house = new House();
        byte[] firstBuffer = null;
        byte[] secondBuffer = null;

        List<Animal> animals = Arrays.asList(
                new Animal("Bosco the dog", house),
                new Animal("Ralph the hamster", house),
                new Animal("Molly the cat", house)
        );

        LOGGER.info("Original animals: {}", animals);

        try (ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
             ObjectOutputStream o1 = new ObjectOutputStream(buf1)) {

            o1.writeObject(animals);
            o1.writeObject(animals); // Write a 2nd set

            firstBuffer = buf1.toByteArray();
            LOGGER.info("Written {} bytes to the first buffer", firstBuffer.length);
        }
        // Write to a different stream:
        try (ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
             ObjectOutputStream o2 = new ObjectOutputStream(buf2)) {

            o2.writeObject(animals);
            secondBuffer = buf2.toByteArray();

            LOGGER.info("Written {} bytes to the second buffer", secondBuffer.length);
        }

        List animals1, animals2, animals3;

        // Now get them back:
        try (ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(firstBuffer))) {

            animals1 = (List) in1.readObject();
            animals2 = (List)in1.readObject();
        }

        try (ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(secondBuffer))) {
            animals3 = (List) in2.readObject();
        }

        LOGGER.info("animals1: {}", animals1);
        LOGGER.info("animals2: {}", animals2);
        LOGGER.info("animals3: {}", animals3);
    }
}
