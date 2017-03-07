package ekkel.book.typeinfo.pets;

import ekkel.book.typeinfo.factory.Factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cresh on 10.08.16.
 */
public class LiteralPetCreator extends PetCreator {

    @SuppressWarnings("unchecked")
    public static final List<Factory<? extends Pet>> allTypes = null;
//////    private static final List<Class<? extends Pet>> types =
//////            allTypes.subList(allTypes.indexOf(Mutt.class),allTypes.size());
//
//    @Override
    public List<Factory<? extends Pet>> types() { return null; }
//
//    public static void main(String[] args) {
//        System.out.println(types);
//    }
}
