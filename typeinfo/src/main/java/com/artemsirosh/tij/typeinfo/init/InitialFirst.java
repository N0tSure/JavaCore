package com.artemsirosh.tij.typeinfo.init;

/**
 * Created by cresh on 07.08.16.
 */
public class InitialFirst {
    static final int statFFirst = 47;
    static final int statFSec = ClassInitialization.random.nextInt(1000);
    static {
        System.out.println("InitiableFirst: initialization");
    }

}
