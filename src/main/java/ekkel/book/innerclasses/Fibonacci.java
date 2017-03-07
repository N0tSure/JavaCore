package ekkel.book.innerclasses;

/**
 * Created by cresh on 01.07.16.
 */
public class Fibonacci {
    private static Integer[] getConsequence(final int n) {
        Integer[] result = new Integer[n];
        result[0] = 0;
        result[1] = 1;

        for (int i = 1; i < n-1; i++) {
            result[i+1] = result[i-1] + result[i];
        }

        return result;
    }


    public static void main(String[] args) {
        Integer[] fib = getConsequence(50);
        for (Integer integer : fib) {
            System.out.println(integer);
        }
    }
}
