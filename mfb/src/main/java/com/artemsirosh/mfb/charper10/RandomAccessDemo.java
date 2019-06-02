package com.artemsirosh.mfb.charper10;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by cresh on 07.02.16.
 */
public class RandomAccessDemo {
    public static void main(String[] args) throws IOException {
        Random rnd = new Random();
        double[] nums = new double[rnd.nextInt(100)];
        RandomAccessFile raf;
        double d;

        for (int i = 0; i < nums.length; i++) {
            nums[i]=rnd.nextDouble();
        }

        try {
            raf = new RandomAccessFile("random.dat","rw");
        } catch (IOException exc) {
            System.out.println("Error by opening file:" + exc);
            return;
        }

        try {
            for (double num : nums) {
                raf.writeDouble(num);
            }
        } catch (IOException exc) {
            System.out.println("Error occuring file writing: " + exc);
        }

        try {
            raf.seek(0);
            d=raf.readDouble();
            System.out.println("First value " + d);

            raf.seek(8);
            d=raf.readDouble();
            System.out.println("Second value " + d);

            raf.seek(8*3);
            d=raf.readDouble();
            System.out.println("Third value " + d);

            System.out.println();

            for (int i = 0; i < nums.length; i+=2) {
                raf.seek(8*i);
                System.out.println();
                System.out.println(raf.readDouble());
            }
        } catch (IOException exc) {
            System.out.println("Error occur reading file: " + exc);
        }

        for (double num : nums) {
            System.out.println(num);
        }

        raf.close();
    }
}
