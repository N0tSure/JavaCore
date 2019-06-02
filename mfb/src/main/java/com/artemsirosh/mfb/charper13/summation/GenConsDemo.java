package com.artemsirosh.mfb.charper13.summation;

/**
 * Created by cresh on 15.04.16.
 */
class GenConsDemo {
    public static void main(String[] args) {
        Summation ob = new Summation(3.0);
        RecSumm rs = new RecSumm(3.0f);
        System.out.println("Summation: " + ob.getSum());
        System.out.println("RecSumm: " + rs.getSum());
    }
}
