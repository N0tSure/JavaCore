package com.artemsirosh.mfb.charper10;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * Created by cresh on 20.01.16.
 */
public class CompFiles {
    public static void main(String[] args) throws IOException {
        int i,j,pos=0;
        FileInputStream f1=null;
        FileInputStream f2=null;

        if (args.length!=2) {
            System.out.println("Использование: CompFiles файл1 файл2");
            return;
        }

        try {
            f1 = new FileInputStream(args[0]);
            f2 = new FileInputStream(args[1]);

            do {
                i=f1.read();
                j=f2.read();
                pos++;
                if (i!=j) break;
            } while (i!=-1 && j!=-1);

            if (i!=j) System.out.println("Содержимое различно. Различие на позиции: " + pos);
            else System.out.println("Содержимое совпадает.");
        } catch (IOException exc) {
            System.out.println("Ошибка ввода-вывода" + exc);
        } finally {
            try {
                if ( f1!=null ) f1.close();
            }  catch (IOException exc) {
                System.out.println("Ошибка при закрытии файла: " + exc);
            }

            try {
                if (f2!=null) f2.close();
            } catch (IOException exc) {
                System.out.println("Ошибка при закрытии файла: " + exc);
            }
        }
    }
}
