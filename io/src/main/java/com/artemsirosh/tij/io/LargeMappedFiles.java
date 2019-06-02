package com.artemsirosh.tij.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 04 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class LargeMappedFiles {
    private static final int DEF_LENGTH = 0x8FFFFFF;
    private static final Logger LOGGER = LoggerFactory.getLogger(LargeMappedFiles.class);

    public static void main(String[] args) throws IOException {
        int fileLength;
        if (args.length == 1) {
            fileLength = new Integer(args[0]);
        } else {
            fileLength = DEF_LENGTH;
        }

        MappedByteBuffer output = new RandomAccessFile("tmp.dat", "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, fileLength);

        for (int i = 0; i < fileLength; i++) {
            output.put((byte) 'x');
        }

        LOGGER.info("Finished writing");

        StringBuilder builder = new StringBuilder();
        for (int i = fileLength/2; i < fileLength / 2 + 6; i++) {
            builder.append((char) output.get(i));
        }

        LOGGER.info("Middle: {}", builder);
    }
}
