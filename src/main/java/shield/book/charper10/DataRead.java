package shield.book.charper10;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by cresh on 20.01.16.
 */
public class DataRead {
    public static void main(String[] args) throws IOException {
        int i;
        boolean bln;
        double db;

        try {
            DataInputStream dataIn = new DataInputStream(new FileInputStream("test.dll"));

            while (true) {
                i=dataIn.readInt();
                System.out.println("Прочитано: " + i);

                db=dataIn.readDouble();
                System.out.println("Прочитано: " + db);

                bln=dataIn.readBoolean();
                System.out.println("Прочитано: " + bln);
            }
        } catch (IOException exc) {
            System.out.println("Ошибка: " + exc);
        }
    }
}
