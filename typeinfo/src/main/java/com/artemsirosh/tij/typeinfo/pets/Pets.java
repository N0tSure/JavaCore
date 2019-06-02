package com.artemsirosh.tij.typeinfo.pets;

import java.util.ArrayList;

/**
 * Created by cresh on 10.08.16.
 */
public class Pets {
    public static final PetCreator creator = new PetFactory();
    public static Pet randomPet() {
        return creator.randomPet();
    }
    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }
}
