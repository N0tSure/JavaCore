package com.artemsirosh.tij.io;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class SortedDirectoryList {

    private final File directory;

    SortedDirectoryList(File directory) {
        checkArgument(directory.exists(), String.format("%s not exists", directory.getName()));
        this.directory = directory;
    }

    SortedDirectoryList(String directoryName) {
        this(new File(checkNotNull(directoryName, "Directory must be not null")));
    }

    public List<File> list() {
        return fileCreator(directory.list());
    }

    public List<File> list(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        return fileCreator(directory.list((dir, fileName) -> pattern.matcher(fileName).matches()));
    }

    private List<File> fileCreator(String[] fileNames) {
        Arrays.sort(fileNames);
        return Arrays.stream(fileNames).map(fileName -> new File(directory, fileName)).collect(Collectors.toList());
    }
}
