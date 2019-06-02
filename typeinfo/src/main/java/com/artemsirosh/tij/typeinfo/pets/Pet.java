package com.artemsirosh.tij.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
public class Pet extends Individual {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Pet> {
        @Override
        public Pet create() {
            return new Pet();
        }
    }
    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }

    public void speak() {
        System.out.print(this.getClass().getSimpleName() + ": ");
    }
}
