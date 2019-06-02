package com.artemsirosh.tij.io;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created on 21 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class House implements Serializable {

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("identity", Integer.toHexString(System.identityHashCode(this)))
                .toString();
    }
}
