package shield.book.charper09;

/**
 * Created by cresh on 08.01.16.
 */
public class CustomExcDemo {
    public static void main(String[] args) {
        int[] nums = { 4, 8, 15, 32, 64, 127, 256, 512 };
        int[] denom = {4, 8, 0, 8, 4, 0, 2};

        for (int i = 0; i < nums.length; i++) {
            try {
                if (!((nums[i]&1)==0)) throw new NoIntResultException(nums[i],denom[i]);
                System.out.println(nums[i] + " / " + denom[i] + " = " + nums[i]/denom[i]);
            } catch (ArithmeticException exc) {
                System.out.println("Divide by zero attempt!");
            } catch (ArrayIndexOutOfBoundsException exc) {
                System.out.println("Out of array!");
            } catch (NoIntResultException exc) {
                System.out.println(exc);
            }
        }
    }
}
