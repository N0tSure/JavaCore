package ekkel.book.io;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class FileSizeCounter extends HashMap<String, Long> {

    private final File directory;

    public FileSizeCounter(File directory) {
        checkArgument(directory.exists(), String.format("%s not exists", directory.getName()));
        this.directory = directory;
        fileCreator(directory.list())
                .stream()
                .filter(file -> !file.isDirectory())
                .forEach(file -> this.put(file.getName(), file.length()));
    }

    public FileSizeCounter(String directoryName) {
        this(new File(checkNotNull(directoryName, "Directory name must not be null")));
    }

    private List<File> fileCreator(String[] fileNames) {
        return Arrays.stream(fileNames).map(fileName -> new File(directory, fileName)).collect(Collectors.toList());
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append(String.format("Survey for \"%s\"\n", directory.getName()));

        for (Map.Entry<String, Long> entry : this.entrySet()) {
            result.append(String.format("%-20.20s : %8d bytes\n", entry.getKey(), entry.getValue()));
        }

        result.append('\n');

        return result.toString();
    }
}
