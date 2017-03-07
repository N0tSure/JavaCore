package ekkel.book.typeinfo.pets;

import ekkel.book.typeinfo.factory.Factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cresh on 10.08.16.
 */
public class PetFactory extends PetCreator {
    @SuppressWarnings("unchecked")
    public static final List<Factory<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(
                    new Pet.Factory(), new Dog.Factory(), new Cat.Factory(),
                    new Rodent.Factory(), new Mutt.Factory(), new Pug.Factory(),
                    new EgyptianMau.Factory(), new Manx.Factory(),
                    new Cymric.Factory(), new Rat.Factory(), new Mouse.Factory(),
                    new Hamster.Factory(), new Gerbil.Factory()
            ));

    private static final List<Factory<? extends Pet>> types =
            allTypes.subList(allTypes.size()/2,allTypes.size());

    @Override
    public List<Factory<? extends Pet>> types() {
        return types;
    }
}
