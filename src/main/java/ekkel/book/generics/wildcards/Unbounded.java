package ekkel.book.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 13.12.16.
 */
class Unbounded {
    private static List nonWildCard;
    private static List<?> unbounded;
    private static List<? extends Object> objectBounded;

    private static void assign1(List list) {
        nonWildCard = list;
        unbounded = list;
//        objectBounded = list; // Warning: unchecked conversion
        // Found: List, Required: List<? extends Object>
    }
    private static void assign2(List<?> list) {
        nonWildCard = list;
        unbounded = list;
        objectBounded = list;
    }
    private static void assign3(List<? extends Object> list) {
        nonWildCard = list;
        unbounded = list;
        objectBounded = list;
    }
    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2(new ArrayList());
//        assign3(new ArrayList()); // Warning:
        // Unchecked conversion. Found: ArrayList
        // Required: List<? extends Object>
        assign1(new ArrayList<String>());
        assign2(new ArrayList<String>());
        assign3(new ArrayList<String>());
        // Both forms are acceptable as List<?>:
        List<?> wildList = new ArrayList();
        wildList = new ArrayList<String>();
        assign1(wildList);
        assign2(wildList);
        assign3(wildList);
    }
}
