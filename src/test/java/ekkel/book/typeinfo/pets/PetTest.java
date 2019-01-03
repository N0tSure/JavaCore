package ekkel.book.typeinfo.pets;

import ekkel.book.generics.latency.Apply;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 19.12.16.
 */
public class PetTest {
    @Test
    public void test() throws Exception {
        PetCreator creator = new PetFactory();
        List<Pet> pets = creator.arrayList(10);
        Apply.apply(pets, Pet.class.getMethod("speak"));
    }
}
