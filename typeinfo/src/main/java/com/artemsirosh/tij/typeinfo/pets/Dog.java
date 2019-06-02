package com.artemsirosh.tij.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
public class Dog extends Pet {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Dog> {
        @Override
        public Dog create() {
            return new Dog();
        }
    }
    public Dog(String name) {
        super(name);
    }

    public Dog() {
        super();
    }

    @Override
    public void speak() {
        super.speak();
        System.out.println("Gav!");
    }
}
