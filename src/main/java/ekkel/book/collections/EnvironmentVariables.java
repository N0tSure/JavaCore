package ekkel.book.collections;

import java.util.Map;

/**
 * Created by cresh on 15.03.16.
 */
public class EnvironmentVariables {
    public static void main(String[] args) {
        for (Map.Entry entry: System.getenv().entrySet()) System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
