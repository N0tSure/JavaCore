package ekkel.book.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

/**
 * <p>
 * Created on 21.06.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class SerializationControlTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void serializationSimple() throws Exception {
        File tmp = temporaryFolder.newFile();

        System.out.println("Constructing objects: ");

        One one = new One();
        Same same = new Same();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tmp))){

            System.out.println("Saving objects:");
            outputStream.writeObject(one);
            outputStream.writeObject(same);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(tmp))){

            System.out.println("Deserialization one: ");
            one = (One) inputStream.readObject();

            System.out.println("Deserialization same: ");
            same = (Same) inputStream.readObject();
        }

    }

    @Test
    public void serializationOrdinal() throws Exception {

        System.out.println("Constructing objects: ");
        Another another = new Another(42, "A String");
        System.out.println("Original: " + another);

        File tmp = temporaryFolder.newFile();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(tmp))) {

            System.out.println("Saving object: ");
            outputStream.writeObject(another);
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(tmp))) {
            System.out.println("Recovering object: ");
            another = (Another) inputStream.readObject();
            System.out.println("Recovered: " + another);
        }

    }
}
