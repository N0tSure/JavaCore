package shield.book.charper10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Created by cresh on 19.01.16.
 */
public class ShowFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream fileIn;

        if (args.length != 1) {
            System.out.println("Usage: ShowFile filename");
            return;
        }

        try {
            fileIn = new FileInputStream(args[0]);
        } catch (FileNotFoundException exc) {
            System.out.println("File not found.");
            return;
        }

        try {
            do {
                i = fileIn.read();
                if (i!=-1) System.out.print((char) i);
            } while (i!=-1);
        } catch (IOException exc) {
            System.out.println("File Read Error");
        } finally {
            try {
                fileIn.close();
            } catch (IOException exc) {
                System.out.println("Error while file closing.");
            }
        }

    }
}
