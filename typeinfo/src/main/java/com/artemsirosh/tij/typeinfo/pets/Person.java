package com.artemsirosh.tij.typeinfo.pets;

import com.artemsirosh.tij.util.Null;

/**
 * Created by cresh on 09.08.16.
 */
public class Person extends Individual {
    private final String last;
    private final String address;
    public Person(String name) {
        super(name);
        last = "";
        address = "";
    }

    public Person(String first, String last, String address) {
        super(first);
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%1$s %2$s %3$s",super.toString(),last,address);
    }

    public static class NullPerson extends Person implements Null {
        private NullPerson() {
            super("None","none","none");
        }

        @Override
        public String toString() {
            return "NullPerson";
        }
    }
    public static final Person NULL = new NullPerson();
}
