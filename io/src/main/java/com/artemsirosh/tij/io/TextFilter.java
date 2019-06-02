package com.artemsirosh.tij.io;

import com.artemsirosh.tij.util.TextFile;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
class TextFilter implements FilenameFilter {

    private Pattern pattern;

    TextFilter(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }

    @Override
    public boolean accept(File dir, String name) {
        File file = new File(dir.getPath(), name);
        if (file.isFile()) {
            return scanText(new TextFile(file.getPath())) > 0;
        }

        return false;
    }

    private long scanText(List<String> strings) {
        return strings.stream().filter(pattern.asPredicate()).count();
    }
}
