package shield.book.charper09;

import java.io.IOException;

/**
 * Created by cresh on 08.01.16.
 */
public class ThrowsDemo {
    static char prompt(String line) throws IOException {
        System.out.println(line + ": ");
        return (char) System.in.read();
    }

    public static void main(String[] args) {
        char ch;

        try {
            ch = prompt("Enter a letter.");
        } catch (IOException exc) {
            System.out.println("Input-Output Exception");
            ch = 'X';
        }

        System.out.println("You press: " + ch);
    }
}
