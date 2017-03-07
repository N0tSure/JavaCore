package ekkel.book.strings;

/**
 * Created by cresh on 18.07.16.
 */
public class StringsVerifier {
    public static boolean verify(String arg) {
        return arg.matches("^\\p{Upper}.*\\.$");
    }
    public static void main(String[] args) {
        System.out.println(verify("This is string."));
    }
}
