package com.artemsirosh.tij.io;

import java.io.File;
import java.util.List;

/**
 * Created on 23.05.2017.
 * Information about directory tree
 * @author Artemis A. Sirosh
 */
public interface TreeInfo extends Iterable<File> {

    /**
     * Return all files from directory tree
     * @return list of directories
     */
    List<File> getFiles();

    /**
     * Return all directories from directory tree
     * @return list of files
     */
    List<File> getDirectories();

    /**
     * Add other {@code TreeInfo} to this TreeInfo
     * @param other TreeInfo object
     */
    void addAll(TreeInfo other);
}
