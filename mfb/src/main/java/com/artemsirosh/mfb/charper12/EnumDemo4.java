package com.artemsirosh.mfb.charper12;

/**
 * Created by cresh on 30.03.16.
 */
public class EnumDemo4 {
    public static void main(String[] args) {
        Transport one, two, three;

        System.out.println("ordinal():");

        for (Transport transport : Transport.values()) {
            System.out.printf("Enum value %s : %d\n",transport,transport.ordinal());
        }

        one=Transport.AIRPLANE;
        two=Transport.AIRPLANE;
        three=Transport.AIRPLANE;

        if (one.compareTo(two)<0) System.out.printf("%1$s before %2$s",one,two);
        if (one.compareTo(two)>0) System.out.printf("%1$s after %2$s",one,two);
        if (one.compareTo(two)==0) System.out.printf("%1$s equals %2$s",one,two);
    }
}
