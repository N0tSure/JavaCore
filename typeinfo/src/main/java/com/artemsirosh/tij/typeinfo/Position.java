package com.artemsirosh.tij.typeinfo;

import com.artemsirosh.tij.typeinfo.pets.Person;

/**
 * Created by cresh on 15.08.16.
 */
class Position {
    private String title;
    private Person person;

    public Position(String title) {
        this.title = title;
        this.person = Person.NULL;
    }

    public Position(String title, Person person) {
        this.title = title;
        this.person = person;
        if (person==null) this.person=Person.NULL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person==null) this.person=Person.NULL;
    }

    @Override
    public String toString() {
        return String.format("Position: %1$s, %2$s",title,person);
    }
}
