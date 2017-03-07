package ekkel.book.strings;

/**
 * Created by cresh on 18.07.16.
 */
public class IntegerMatch {
    public static void main(String[] args) {
        System.out.println("-1234".matches("-?\\d+"));
        System.out.println("871234".matches("-?\\d+"));
        System.out.println("+71234".matches("-?\\d+"));
        System.out.println("+91256".matches("(-|\\+)?\\d+"));
    }
}
