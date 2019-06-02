package com.artemsirosh.mfb.charper09;

/**
 * Created by cresh on 08.01.16.
 */
public class UseThrowablemethods {
    static void genException() {
        int[] nums = new int[4];

        System.out.println("Before exception.");

        nums[10] = 23;
        System.out.println("This string will not shown.");
    }

    public static void main(String[] args) {
        try {
            genException();
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Standart messege: ");
            System.out.println(exc);
            System.out.println("\nStack calls:");
            //exc.printStackTrace();
            System.out.println(exc.getMessage());

        }
    }
}
