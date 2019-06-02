package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.BinaryFile;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.Assert.fail;

/**
 * Created on 28 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class BinaryFileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataRecoveringTest.class);
    private static final String SMILEY_BMP = "./src/main/resources/smiley.bmp";
    private static final byte[] MAGIC_BYTES = {
            (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE
    };

    @Test
    public void binaryCounter() throws Exception {
        Map<Byte, Integer> result = new HashMap<>();
        byte[] source = BinaryFile.read(SMILEY_BMP);

        IntStream.range(0, source.length).mapToObj(i -> source[i]).forEach(bt -> {
            if (result.containsKey(bt))
                result.put(bt, result.get(bt) + 1);
            else
                result.put(bt, 1);
        });

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Byte, Integer> entry : result.entrySet()) {
            builder.append(String.format("%-8H : %03d\n", entry.getKey(), entry.getValue()));
        }

        LOGGER.info("File {} byte info:\n{}", SMILEY_BMP, builder);
    }

    @Test
    public void classFileChecker() throws Exception {
        TreeInfo info = Directory.walk("target/classes", ".*\\.class");

        for (File file : info.getFiles())
            if (!isClassFileMagicBytesStartsWith(BinaryFile.read(file)))
                fail(file.getName());

        LOGGER.info("{} files checked.", info.getFiles().size());

    }

    private boolean isClassFileMagicBytesStartsWith(byte[] comparison) {

        for (int i = 0; i < MAGIC_BYTES.length; i++) {
            if (MAGIC_BYTES[i] != comparison[i])
                return false;
        }

        return true;
    }

}
