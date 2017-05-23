package ekkel.book.io;

import ekkel.book.util.TextFile;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class TextFilter implements FilenameFilter {

    private Pattern pattern;

    public TextFilter(String regexp) {
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
