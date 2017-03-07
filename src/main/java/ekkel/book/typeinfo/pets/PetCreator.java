package ekkel.book.typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import ekkel.book.typeinfo.factory.Factory;

/**
 * Created by cresh on 09.08.16.
 */
public abstract class PetCreator {
    private Random random = new Random(47);
    public abstract List<Factory<? extends Pet>> types();
    public Pet randomPet() {
        int n = random.nextInt(types().size());
        return types().get(n).create();
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = this.randomPet();
        }
        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<>(size);
        Collections.addAll(result,createArray(size));
        return result;
    }
}
