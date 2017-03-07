package ekkel.book.typeinfo;

import ekkel.book.typeinfo.factory.Factory;
import ekkel.book.typeinfo.pets.LiteralPetCreator;
import ekkel.book.typeinfo.pets.Pet;
import ekkel.book.typeinfo.pets.Pets;
import ekkel.book.util.MapData;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by cresh on 10.08.16.
 */
public class PetCountOne {
    static class PetCounter extends LinkedHashMap<Factory<? extends Pet>,Integer> {

        public PetCounter() {
            super(MapData.map(LiteralPetCreator.allTypes,0));
        }

        public void count(Pet pet) {
            for (Map.Entry<Factory<? extends Pet>, Integer> pair : entrySet())
                if (pair.getKey().create().getClass().isInstance(pet))
                    put(pair.getKey(), pair.getValue() + 1);
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder("{");
            for (Map.Entry<Factory<? extends Pet>, Integer> pair : entrySet()) {
                result.append(pair.getKey().create().getClass().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(", ");
            }
            result.delete(result.length()-2, result.length());
            result.append("}");
            return new String(result);
        }
    }

    public static void main(String[] args) {
        PetCounter petCounter = new PetCounter();
        for (Pet pet : Pets.createArray(20)) {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCounter.count(pet);
        }
        System.out.println();
        System.out.println(petCounter);
    }
}
