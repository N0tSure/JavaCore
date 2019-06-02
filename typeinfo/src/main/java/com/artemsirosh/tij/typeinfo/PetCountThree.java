package com.artemsirosh.tij.typeinfo;

import com.artemsirosh.tij.typeinfo.pets.Pet;
import com.artemsirosh.tij.typeinfo.pets.Pets;

/**
 * Created by cresh on 10.08.16.
 */
public class PetCountThree {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        System.out.println();
        System.out.println(counter);
    }
}
