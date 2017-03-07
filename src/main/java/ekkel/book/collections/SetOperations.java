package ekkel.book.collections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by NotSure on 03.03.16.
 */
public class SetOperations {
    public static void main(String[] args) {
        Set<String> setOne = new HashSet<String>();
        Collections.addAll(setOne,"A B C D E F G H I J K L".split(" "));
        setOne.add("M");
        System.out.println("H: " + setOne.contains("H"));
        System.out.println("N: " + setOne.contains("N"));
        Set<String> setTwo = new HashSet<String>();
        Collections.addAll(setTwo, "H I J K L".split(" "));
        System.out.println("setTwo in setOne: " + setOne.containsAll(setTwo));
        setOne.remove("H");
        System.out.println("setOne: " + setOne);
        System.out.println("setTwo in setOne: " + setOne.containsAll(setTwo));
        setOne.removeAll(setTwo);
        System.out.println("setTwo removed from setOne: " + setOne);
        Collections.addAll(setOne,"X Y Z".split(" "));
        System.out.println("X Y Z added to setOne" + setOne);
    }
}
