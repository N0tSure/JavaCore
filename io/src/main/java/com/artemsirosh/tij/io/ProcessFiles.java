package com.artemsirosh.tij.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class ProcessFiles {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessFiles.class);

    private final FileProcessor processor;
    private final Pattern pattern;

    public ProcessFiles(FileProcessor processor, String regexp) {
        this.processor = processor;
        this.pattern = Pattern.compile(regexp);
    }

    void start(String... fileNames) {
        try {
            if (fileNames == null || fileNames.length == 0) {

                processDirectoryTree(new File("."));
            } else {

                for (String fileName : fileNames) {

                    File file = new File(fileName);

                    if (file.isDirectory()) {

                        processDirectoryTree(file);
                    } else {

                        processor.process(new File(fileName).getCanonicalFile());
                    }
                }
            }
        } catch (IOException exc) {
            LOGGER.error("Error due processing", exc);
        }
    }

    private void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(root.getAbsolutePath(), pattern.pattern()))
            processor.process(file);
    }
}
