package ekkel.book.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class FilesSizeInfo implements TreeInfo {

    private List<File> files;

    public FilesSizeInfo() {
        files = new ArrayList<>();
    }

    @Override
    public List<File> getFiles() {
        return files;
    }

    @Override
    public List<File> getDirectories() {
        return Collections.emptyList();
    }

    @Override
    public void addAll(TreeInfo other) {
        this.files.addAll(other.getFiles());
    }

    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }

    @Override
    public String toString() {
        long total = 0;
        StringBuilder result = new StringBuilder();
        for (File file : files) {
            result.append(String.format("%-20.20s : %8d\n", file.getName(), file.length()));
            total += file.length();
        }

        result.append(String.format("\n\tTotal: %d bytes\n", total));

        return result.toString();
    }
}
