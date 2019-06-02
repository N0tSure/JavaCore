package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.TextFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.*;

/**
 * Created on 18 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class ZipCompressingTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCompressingTest.class);
    private static final File RESOURCES = new File("/home/train/Core/src/main/resources");

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();


    @Test
    public void archiveCreation() throws Exception {
        File archive = temporaryFolder.newFile("resources.zip");
        LOGGER.info("Created archive: {}", archive);
        LOGGER.info("Source file: {}, size: {}", RESOURCES, RESOURCES.length());

        try (FileOutputStream fileOutputStream = new FileOutputStream(archive);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
             ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
             WritableByteChannel outputChannel = Channels.newChannel(zipOutputStream)
             ) {

                 zipOutputStream.setComment("Test zipping");

                 if (RESOURCES.listFiles() != null) {

                     for (File file : RESOURCES.listFiles()) {

                         LOGGER.info("Added new zip entry: {}, uncompressed size: {}", file, file.length());
                         FileChannel inputChannel = new FileInputStream(file).getChannel();
                         zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                         inputChannel.transferTo(0, file.length(), outputChannel);
                         inputChannel.close();
                     }
                 }

                 LOGGER.info("Compressed file: {}, size: {}", archive, archive.length());
                 LOGGER.info("Compressed checksum: {}", checkedOutputStream.getChecksum().getValue());
        }


        try (
                CheckedInputStream checkedStream = new CheckedInputStream(new FileInputStream(archive), new Adler32());
                ZipInputStream zip = new ZipInputStream(checkedStream);
                ReadableByteChannel readable = Channels.newChannel(zip);
                ) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {

                File entryFile = temporaryFolder.newFile(entry.getName());
                LOGGER.info("Reading entry: {}, new file: {}", entry, entryFile);

                try (FileChannel channel = new FileOutputStream(entryFile).getChannel()) {

                    readable.read(buffer);
                    do {
                        channel.write(buffer);
                        buffer.clear();
                    } while (readable.read(buffer) > 0);

                }

                LOGGER.info("Uncompressed file {}, size: {}", entryFile, entryFile.length());
                LOGGER.info("Content: \n{}", TextFile.read(entryFile.getAbsolutePath()));
            }
        }
    }
}
