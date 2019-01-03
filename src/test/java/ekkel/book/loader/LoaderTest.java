package ekkel.book.loader;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Created by cresh on 09.12.16.
 */
public class LoaderTest {

    @Test
    public void searchFileTest() {
        File lib = new File("lib");

        System.out.println("File exist: " + (lib.exists() ? "yes" : "no"));
        Assert.assertTrue(lib.exists());

        System.out.println("Directory: " + (lib.isDirectory() ? "yes" : "no"));
        Assert.assertTrue(lib.isDirectory());

        System.out.println("Contain files: " + (lib.listFiles()!=null ? "yes" : "no"));
        Assert.assertNotNull(lib.listFiles());

        for (File file : lib.listFiles())
            System.out.println("Contains file: " + file.getName());
    }

    @Test
    public void readingTest() throws IOException {
        File lib = new File("lib");
        File[] classFiles = lib.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        byte[] sec = null;
        byte[] module = null;
        if (classFiles!=null) {
            module = getBytesSecond(classFiles[0]);
            sec = getBytesFirst(classFiles[0]);
        }
        Assert.assertNotNull(module);
        Assert.assertArrayEquals(sec, module);
        for (int i = 0; i < module.length; i++) {
            System.out.printf("%02X ", module[i]);
            if (i%30==0) System.out.println();
        }
    }

    private byte[] getBytesFirst(File file) throws IOException {
        long flen = file.length();
        byte[] result = new byte[(int) flen];
        try (InputStream inputStream = new FileInputStream(file)) {

            int offset = 0;
            int numRead;
            while (offset < result.length &&
                    (numRead = inputStream.read(result, offset, result.length - offset)) >= 0)
                offset += numRead;

            if (offset < result.length)
                throw new IOException("Could not completely read file " + file.getName());
        }

        return result;
    }

    private byte[] getBytesSecond(File file) throws IOException {
        long flen = file.length();
        byte[] result = new byte[(int) flen];
        try (InputStream inputStream = new FileInputStream(file)) {
            if (flen < inputStream.read(result))
                throw new IOException("Could not completely read file " + file.getName());
        }

        return result;
    }
}
