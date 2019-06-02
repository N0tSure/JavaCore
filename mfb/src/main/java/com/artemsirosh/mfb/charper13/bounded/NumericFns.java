package com.artemsirosh.mfb.charper13.bounded;

/**
 * Created by cresh on 11.04.16.
 */
class NumericFns<T extends Number> {
    private T num;
    NumericFns(T num) {
        this.num=num;
    }

    public T getNum() {
        return num;
    }

    double reciprocal() {
        return 1/num.doubleValue();
    }

    double fraction() {
        return num.doubleValue() - num.intValue();
    }

    boolean absEquals(NumericFns<?> numericFns) {
        return (Math.abs(num.doubleValue())==Math.abs(numericFns.num.doubleValue()));
    }
}
