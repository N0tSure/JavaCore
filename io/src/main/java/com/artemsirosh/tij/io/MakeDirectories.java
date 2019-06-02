package com.artemsirosh.tij.io;

import com.google.common.collect.ImmutableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 24.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class MakeDirectories {
    static String usage() {
        return "Usage: directories path1 ...\n" +
                "Creates each path\n" +
                "Usage: directories -d path1 ...\n" +
                "Deletes each path\n" +
                "Usage: directories -r path1 path2\n" +
                "Renames from path1 to path2\n";
    }

    static String fileData(File file) {
        String format = "%-15.15s:  %-10.30s\n";
        return String.format(format, "Absolute path", file.getAbsolutePath()) +
                String.format(format, "Can read", String.valueOf(file.canRead())) +
                String.format(format, "Can write", String.valueOf(file.canWrite())) +
                String.format(format, "File name", file.getName()) +
                String.format(format, "Parent name", file.getParent()) +
                String.format(format, "Path name", file.getPath()) +
                String.format(format, "Length", file.length()) +
                String.format(format, "Last modified", new Date(file.lastModified())) +
                String.format(format, "Is File", String.valueOf(file.isFile())) +
                String.format(format, "Is Directory", String.valueOf(file.isDirectory()));
    }

    static File renameFile(String oldFileName, String newFileName) {
        File old = new File(oldFileName);
        File newFile = new File(newFileName);
        if (old.renameTo(newFile)) {
            return newFile;
        } else {
            return null;
        }
    }

    static Iterable<File> createDirectories(String[] fileNames, int startIndex, int length) {
        return operateFiles(fileNames, startIndex, length, false);
    }

    static Iterable<File> deleteDirectories(String[] fileNames, int startIndex, int length) {
        return operateFiles(fileNames, startIndex, length, true);
    }

    private static Iterable<File> operateFiles(String[] fileNames, int startIndex, int length, boolean delete) {

        List<File> result = new ArrayList<>();

        for (int i = startIndex; i < length; i++) {
            File file = new File(fileNames[i]);
            if (file.exists()) {

                if (delete && file.delete())
                    result.add(file);
            } else {

                if (!delete && file.mkdir()) {
                    result.add(file);
                }
            }
        }

        return ImmutableList.copyOf(result);
    }



}
