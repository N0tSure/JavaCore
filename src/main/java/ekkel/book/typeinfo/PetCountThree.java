package ekkel.book.typeinfo;

import ekkel.book.typeinfo.pets.Pet;
import ekkel.book.typeinfo.pets.Pets;

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
