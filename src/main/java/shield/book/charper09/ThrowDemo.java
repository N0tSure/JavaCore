package shield.book.charper09;

/**
 * Created by cresh on 06.01.16.
 */
public class ThrowDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Before throw.");
            throw new ArithmeticException();
        } catch (ArithmeticException exc) {
            System.out.println("Catched!");
        }
        System.out.println("After throw.");
    }
}
