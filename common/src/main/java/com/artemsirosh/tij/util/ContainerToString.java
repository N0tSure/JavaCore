package com.artemsirosh.tij.util;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 23.05.2017.
 *
 * @author Artemis A. Sirosh
 */
public class ContainerToString {
    public static String format(Collection<?> collection) {
        if (collection.isEmpty() || collection.size() == 1)
            return collection.toString();

        StringBuilder builder = new StringBuilder("[\n");


        for (Object item : collection)
            builder.append(item).append('\n');

        return builder.append("\n]").toString();
    }

    public static String format(Object[] objects) {
        return format(Arrays.asList(objects));
    }
}
