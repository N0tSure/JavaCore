package com.artemsirosh.tij.io.xml;

import com.google.common.base.Objects;

import static com.google.common.base.Objects.equal;

/**
 * Created on 24 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class Person {

    private String firstName, lastName;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName);
    }

    @Override
    public boolean equals(Object that) {
        return this == that ||
                ( !equal(null, that) &&
                        that instanceof Person &&
                        equal(this.firstName, ((Person) that).firstName) &&
                        equal(this.lastName, ((Person) that).lastName)
                );
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
