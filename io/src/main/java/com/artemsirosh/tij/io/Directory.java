package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.ContainerToString;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class Directory {

    static File[] local(File directory, String regexp) {
        final Pattern pattern = Pattern.compile(regexp);
        return directory.listFiles(
                (dir, name) -> pattern.matcher(new File(name).getName()).matches()
        );
    }

    static File[] local(String path, String regexp) {
        return local(new File(checkNotNull(path)), regexp);
    }

    static TreeInfo walk(File directory, String regexp) {
        return recursiveWalk(directory, regexp);
    }

    static TreeInfo walk(String fileName, String regexp) {
        return recursiveWalk(new File(fileName), regexp);
    }

    static TreeInfo walk(File directory) {
        return recursiveWalk(directory, ".*");
    }

    static TreeInfo walk(String fileName) {
        return recursiveWalk(new File(fileName), ".*");
    }

    private static TreeInfo recursiveWalk(File root, String regexp) {

        File[] files;

        if (root != null && (files = root.listFiles()) != null ) {
            TreeInfo result = new TreeInfoImpl();

            for (File file : files) {
                if (file.isDirectory()) {
                    result.getDirectories().add(file);
                    result.addAll(recursiveWalk(file, regexp));
                } else {
                    if (file.getName().matches(regexp))
                        result.getFiles().add(file);
                }
            }

            return result;
        }

        return new TreeInfoImpl();
    }

    private static class TreeInfoImpl implements TreeInfo {

        private List<File> files;
        private List<File> directories;

        private TreeInfoImpl() {
            this.files = new ArrayList<>();
            this.directories = new ArrayList<>();
        }

        @Override
        public List<File> getFiles() {
            return this.files;
        }

        @Override
        public List<File> getDirectories() {
            return this.directories;
        }

        @Override
        public void addAll(TreeInfo other) {
            this.files.addAll(other.getFiles());
            this.directories.addAll(other.getDirectories());
        }

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        @Override
        public String toString() {
            return "directories: " +
                    ContainerToString.format(directories) +
                    "\n\nfiles: " +
                    ContainerToString.format(files);
        }


    }
}
