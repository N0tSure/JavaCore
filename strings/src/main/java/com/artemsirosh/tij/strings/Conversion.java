package com.artemsirosh.tij.strings;

import java.math.BigInteger;
import java.util.Formatter;
import java.util.Random;

/**
 * Created by cresh on 12.02.16.
 */
public class Conversion {
    public static void main(String[] args) {
        Formatter formatter = new Formatter(System.out);
        Random random = new Random();

        char value = 'a';
        System.out.println("value: " + value);
        formatter.format("s: %s\n",value);
        //formatter.format("d: %d\n",value);
        formatter.format("c: %c\n",value);
        formatter.format("b: %b\n",value);
        //formatter.format("f: %f\n",value);
        //formatter.format("e: %e\n",value);
        //formatter.format("x: %x\n",value);
        formatter.format("h: %h\n",value);

        int number = random.nextInt(Integer.MAX_VALUE);
        System.out.println("number: " + number);
        formatter.format("s: %s\n",number);
        formatter.format("d: %d\n",number);
        formatter.format("c: %c\n",number>200 ? 200 : number);
        formatter.format("b: %b\n",number);
        //formatter.format("f: %f\n",number);
        //formatter.format("e: %e\n",number);
        formatter.format("x: %5x\n",number);
        formatter.format("h: %h\n",number);

        BigInteger integer = new BigInteger("50000000000000");
        System.out.println("integer " + integer);
        formatter.format("s: %s\n",integer);
        formatter.format("d: %d\n",integer);
        //formatter.format("c: %c\n",integer);
        formatter.format("b: %b\n",integer);
        //formatter.format("f: %f\n",integer);
        //formatter.format("e: %e\n",integer);
        formatter.format("x: %5x\n",integer);
        formatter.format("h: %h\n",integer);

        BigInteger bigInteger = new BigInteger("50000000000000");
        System.out.println("natural " + bigInteger);
        formatter.format("s: %s\n",bigInteger);
        formatter.format("d: %d\n",bigInteger);
        //formatter.format("c: %c\n",integer);
        formatter.format("b: %b\n",bigInteger);
        //formatter.format("f: %f\n",integer);
        //formatter.format("e: %e\n",integer);
        formatter.format("x: %5x\n",bigInteger);
        formatter.format("h: %h\n",bigInteger);

        double natural = random.nextDouble();
        System.out.println("natural " + natural);
        formatter.format("s: %s\n",natural);
        //formatter.format("d: %d\n",natural);
        //formatter.format("c: %c\n",natural);
        formatter.format("b: %b\n",natural);
        formatter.format("f: %.10f\n",natural);
        formatter.format("e: %e\n",natural);
        //formatter.format("x: %5x\n",natural);
        formatter.format("h: %h\n",natural);

        Conversion conversion = new Conversion();
        System.out.println("conversion " + conversion);
        formatter.format("s: %s\n",conversion);
        //formatter.format("d: %d\n",conversion);
        //formatter.format("c: %c\n",conversion);
        formatter.format("b: %b\n",conversion);
        //formatter.format("f: %.10f\n",conversion);
        //formatter.format("e: %e\n",conversion);
        //formatter.format("x: %5x\n",conversion);
        formatter.format("h: %h\n",conversion);

        boolean logical = random.nextBoolean();
        System.out.println("logical " + logical);
        formatter.format("s: %s\n",logical);
        //formatter.format("d: %d\n",logical);
        //formatter.format("c: %c\n",logical);
        formatter.format("b: %b\n",logical);
        //formatter.format("f: %.10f\n",logical);
        //formatter.format("e: %e\n",logical);
        //formatter.format("x: %5x\n",logical);
        formatter.format("h: %h\n",logical);

    }
}
