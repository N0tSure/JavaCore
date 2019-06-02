package com.artemsirosh.mfb.charper13.twogen;

/**
 * Created by cresh on 21.04.16.
 */
class AutoGen {

    public static void main(String[] args) {
       TwoGen<String, Integer> test = new TwoGen<>("String test",56);
        TwoGen<String, Integer> two = new TwoGen<>("RE",22);
        System.out.println(test.isSame(two.getT()));
        System.out.println(test.isSame(new TwoGen<>("Test",55).getT()));
    }
}
