package shield.book.charper10;

import java.io.*;
/**
 * Created by cresh on 19.01.16.
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        int i;
        FileInputStream inFile = null;
        FileOutputStream outFile = null;

        if (args.length!=2) {
            System.out.println("CopuFile usage: OldFile NewFile");
            return;
        }

        try {
            inFile=new FileInputStream(args[0]);
            outFile= new FileOutputStream(args[1],true);

            do {
                i=inFile.read();
                if (i!=-1) outFile.write(i);
            } while (i!=-1);
        } catch (IOException exc) {
            System.out.println("Error: " + exc);
        } finally {
            try {
                if (inFile!=null) inFile.close();
            } catch (IOException exc) {
                System.out.println("Error while input file closing.");
            }

            try {
                if (outFile!=null) outFile.close();
            } catch (IOException exc) {
                System.out.println("Error while output file closing.");
            }
        }
    }
}
