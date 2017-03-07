package ekkel.book.typeinfo.pets;

import ekkel.book.typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 09.08.16.
 */
public class ForNameCreator extends PetCreator {
//    private static List<Factory<? extends Pet>> types = new ArrayList<>();
//    private static String[] typeNames = {
//            "ekkel.book.typeinfo.pets.Mutt",
//            "ekkel.book.typeinfo.pets.Pug",
//            "ekkel.book.typeinfo.pets.EgyptianMau",
//            "ekkel.book.typeinfo.pets.Manx",
//            "ekkel.book.typeinfo.pets.Cymric",
//            "ekkel.book.typeinfo.pets.Rat",
//            "ekkel.book.typeinfo.pets.Mouse",
//            "ekkel.book.typeinfo.pets.Hamster",
//            "ekkel.book.typeinfo.pets.Gerbil"
//    };
//    @SuppressWarnings("unchecked")
//    private static void loader() {
//        try {
//            for (String name : typeNames)
//                types.add((Factory<? extends Pet>) Class.forName(name));
//        } catch (ClassNotFoundException exc) {
//            throw new RuntimeException(exc);
//        }
//    }
//    static { loader(); }
//
//    @Override
    public List<Factory<? extends Pet>> types() { return null; }
}
