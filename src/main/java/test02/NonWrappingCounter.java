package test02;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>
 *     Created on 12 Jun, 2017.
 * </p>
 * <p>
 *     Counts characters in mapped file, while
 *     it's search character, this {@link CharacterCounter}
 *     realisation don't wrap ByteBuffer into CharBuffer,
 *     in other words search character by bytes into target file.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class NonWrappingCounter implements CharacterCounter {

    @Override
    public int countCharacters(char keyChar, final String target) throws Exception {

        byte[] keyCharBytes = String.valueOf(keyChar).getBytes("UTF-8");
        int result = 0;

        try (FileChannel channel = new RandomAccessFile(target, "r").getChannel()) {

            ByteBuffer bufferedFile =
                    channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());


            int smallCycleIndicator = 0;
            fileCycle: while (bufferedFile.hasRemaining()) {

                for (; smallCycleIndicator < keyCharBytes.length; smallCycleIndicator++) {

                    if (Byte.compare(keyCharBytes[smallCycleIndicator], bufferedFile.get()) != 0) {
                        smallCycleIndicator = 0;
                        continue fileCycle;
                    }

                }

                smallCycleIndicator = 0;
                result++;
            }

        }

        return result;
    }

}
