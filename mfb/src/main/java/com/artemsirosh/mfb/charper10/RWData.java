package com.artemsirosh.mfb.charper10;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by cresh on 20.01.16.
 */
public class RWData {
    public static void main(String[] args) throws IOException {
        Random rnd = new Random();
        int i;
        boolean bln;
        double db;

        try {
            DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("test.dll"));

            while (true) {
                i=rnd.nextInt();
                System.out.println("Записано: " + i);
                dataOut.writeInt(i);

                db=rnd.nextDouble();
                System.out.println("Записано: " + db);
                dataOut.writeDouble(db);

                bln=rnd.nextBoolean();
                System.out.println("Записано: " + bln);
                dataOut.writeBoolean(bln);
            }
        } catch (IOException exc) {
            System.out.println("Ошибка при записи.");
            return;
        }


    }
}
