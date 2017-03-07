package shield.book.charper09;

/**
 * Created by cresh on 08.01.16.
 */
public class UseFinally {
    static void getException(int what) {
        int t;
        int[] nums = new int[2];

        System.out.println("Define what: " + what);
        try {
            switch (what) {
                case 0:
                    t = 10/what;
                    break;
                case 1:
                    nums[4]=what;
                    break;
                case 2:
                    return;
            }
        } catch (ArithmeticException exc) {
            System.out.println("Divide by zero attempt.");
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Index out of array");
        } finally {
            System.out.println("Escape from try.");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            getException(i);
            System.out.println();
        }
    }
}
