package com.artemsirosh.tij.generics.wildcards;

/**
 * Created by cresh on 12.12.16.
 */
class CovariantArrays {
    static class Value extends Number {
        @Override
        public int intValue() {
            return 0;
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }

    public static void main(String[] args) {
        Fruit[] fruits = new Apple[10];
        fruits[0] = new Apple();
        fruits[1] = new Jonathan();
        try {
            fruits[0] = new Fruit();
        } catch (Exception exc) {
            System.err.println(exc);
        }
        try {
            fruits[0] = new Orange();
        } catch (Exception exc) {
            System.err.println(exc);
        }

        Number[] numbers = new Integer[10];
        numbers[0] = new Integer(1);
        try {
            numbers[1] = new Value();
        } catch (Exception exc) {
            System.err.println(exc);
        }

    }
}
