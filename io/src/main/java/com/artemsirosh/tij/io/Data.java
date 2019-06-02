package com.artemsirosh.tij.io;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * Created on 18 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Data implements Serializable {
    private int number;

    Data(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object that) {
        return !Objects.equal(null, that) &&
                that instanceof Data &&
                Objects.equal(number, ((Data) that).number);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
