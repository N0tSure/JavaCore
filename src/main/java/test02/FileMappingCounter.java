package test02;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * <P>
 *     Created on 12 Jun, 2017.
 *     Counts characters, using mapped file.
 *     For searching character into buffer wraps ByteBuffer in CharBuffer
 * </P>
 *
 * @author Artemis A. Sirosh
 */
public class FileMappingCounter implements CharacterCounter {

    private CharsetDecoder decoder;

    public FileMappingCounter() {
        decoder = Charset.forName("utf-8").newDecoder();
    }

    @Override
    public int countCharacters(char keyChar, String target) throws Exception {
        int result = 0;

        try (FileChannel channel = new RandomAccessFile(target, "r").getChannel()) {

            ByteBuffer bufferedFile =
                    channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            CharBuffer buffer = decoder.decode(bufferedFile);

            while (buffer.hasRemaining())
                if (Character.compare(keyChar, buffer.get()) == 0)
                    result++;

        }

        return result;
    }
}
