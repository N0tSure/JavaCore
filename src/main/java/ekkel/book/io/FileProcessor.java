package ekkel.book.io;

import java.io.File;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
@FunctionalInterface
public interface FileProcessor {
    void process(File file);
}
