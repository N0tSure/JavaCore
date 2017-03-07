package ekkel.book.util;

import java.util.*;

/**
 * Created by cresh on 27.08.16.
 */
public class New {
    public static <K,V> Map<K,V> map() {
        return new HashMap<K,V>();
    }

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    public static <T> LinkedList<T> linkedList() {
        return new LinkedList<T>();
    }

    public static <T> Set<T> set() {
        return new HashSet<T>();
    }

    public static <T> Queue<T> queue() {
        return linkedList();
    }
}
