package shield.book.charper09;

/**
 * Created by cresh on 06.01.16.
 */
public class ReThrowDemo {
    public static void getException() {
        int[] number = {12, 65, 98, 85, 65, 48, 52, 45, 26, 45, 82};
        int[] denom = {12, 58, 0, 86, 58, 0, 8};

        for (int i = 0; i < number.length; i++) {
            try {
                System.out.println(number[i] + " / " + denom[i] + " equals " + number[i] / denom[i]);
            } catch (ArithmeticException exc) {
                System.out.println("Dividing by zero.");
            } catch (ArrayIndexOutOfBoundsException exc) {
                System.out.println("Out of bounds.");
                throw exc;
            }
        }
    }

    public static void main(String[] args) {
        try {
            getException();
        }
        catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("FATAL ERROR");
        }
    }
}

