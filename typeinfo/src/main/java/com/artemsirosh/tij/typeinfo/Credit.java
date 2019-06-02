package com.artemsirosh.tij.typeinfo;

import java.util.Scanner;

/**
 * Created by cresh on 25.08.16.
 */
class Credit {
    static void inorder(String string) {
        StringBuilder odd = new StringBuilder();
        StringBuilder nonOdd = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (i%2==0) {
                nonOdd.append(string.charAt(i));
                nonOdd.append(" ");
            } else {
                odd.append(string.charAt(i));
                odd.append(" ");
            }
        }
        System.out.println("Четные: " + nonOdd);
        System.out.println("Нечетные: " + odd);

    }
    static int validate(final long nums) {
        int degree = (int) Math.round(Math.log10(nums));
        int result = 0;
        long tmp;
        for (int i = 1; i<degree+1; i++) {
            tmp = nums/(long) Math.pow(10,i-1);
            tmp %= 10;
            //System.out.println(tmp);
            if ((i&1)==0) {
                //System.out.print((tmp) + " ");
                if ((tmp<<1) >= 10) {
//                    System.out.print(((tmp % 10) << 1)+" ");
                    result += (tmp << 1) % 10;
                    result += (tmp << 1) / 10;
                } else {
                    //System.out.println((tmp % 10) << 1);
                    result += tmp << 1;
                }
            } else {
                //System.out.print(tmp%10 + " ");
                result += tmp;
            }
        }
//        System.out.printf("\nOdds: %d, no-odds: %d",odds,nodds);
//        return 0;
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        validate(new Long("5396151126499344"));
//        inorder("5396151126499344");
        System.out.print("Number: ");
        int valid = validate(scanner.nextLong());
//        System.out.println(valid);
        System.out.println(valid%10==0 ? "valid" : "invalid");
    }
}
