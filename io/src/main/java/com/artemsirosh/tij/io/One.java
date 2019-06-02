package com.artemsirosh.tij.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * <p>
 * Created on 21.06.2017.
 * </p>
 *
 * @author Artemis A. Sirosh
 */
class One implements Externalizable {

    public One() {
        System.out.println("One's constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("One.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("One.readExternal");
    }
}
