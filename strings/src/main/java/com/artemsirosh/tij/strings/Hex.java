package com.artemsirosh.tij.strings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 13.07.16.
 */
public class Hex {
    private static String format(Byte[] data) {
        StringBuilder result = new StringBuilder();
        int n = 0;
        for (byte b : data) {
            if (n%16==0) result.append(String.format("%05X: ",n));
            result.append(String.format("%02X ",b));
            n++;
            if (n%16==0) result.append("\n");
        }

        result.append("\n");
        return new String(result);
    }

    public static String buildBytesToHex(File file) throws IOException {
        if (!file.exists()) return null;
        List<Byte> bytes = new ArrayList<>();
        FileInputStream reader = new FileInputStream(file);
        while (reader.available()>0) {
            Byte b = (byte) reader.read();
            bytes.add(b);
        }
        Byte[] result = new Byte[bytes.size()];
        result = bytes.toArray(result);
        return format(result);
    }

    public static class Loader {
        public static void main(String[] args) throws IOException {
            System.out.print(Hex.buildBytesToHex(new File("./out/production/Training/ekkel/book/strings/Concatenation.class")));
        }
    }
}
