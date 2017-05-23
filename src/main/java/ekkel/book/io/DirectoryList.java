package ekkel.book.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created on 22.05.2017.
 * Prints files, whose contains directory given in params
 * @author Artemis A. Sirosh
 */
public class DirectoryList {
    public static void main(String[] args) {
        File path = new File("./src/main/java/ekkel/book/generics");
        String[] list;
        if (args.length == 0)
            list = path.list();
        else
            list = path.list(new TextFilter(args[0]));

        if (list != null) {
            Arrays.sort(list, String::compareToIgnoreCase);
            for (String item : list) {
                System.out.println(item);
            }
        }
    }
}
