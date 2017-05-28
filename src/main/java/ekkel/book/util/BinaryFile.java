package ekkel.book.util;

import static com.google.common.base.Preconditions.*;

import java.io.*;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class BinaryFile {

    public static byte[] read(File file) throws IOException {
        byte[] resultData = new byte[]{};

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            resultData = new byte[inputStream.available()];
            inputStream.read(resultData);
        }

        return resultData;
    }

    public static byte[] read(String fileName) throws IOException {
        File file = new File(fileName);
        checkArgument(file.exists());
        checkArgument(file.canRead());

        return read(file.getAbsoluteFile());
    }
}
