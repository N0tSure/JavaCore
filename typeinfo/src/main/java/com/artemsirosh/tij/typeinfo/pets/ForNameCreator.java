package com.artemsirosh.tij.typeinfo.pets;

import com.artemsirosh.tij.typeinfo.factory.Factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 09.08.16.
 */
public class ForNameCreator extends PetCreator {
//    private static List<Factory<? extends Pet>> types = new ArrayList<>();
//    private static String[] typeNames = {
//            "com.artemsirosh.tij.typeinfo.pets.Mutt",
//            "com.artemsirosh.tij.typeinfo.pets.Pug",
//            "com.artemsirosh.tij.typeinfo.pets.EgyptianMau",
//            "com.artemsirosh.tij.typeinfo.pets.Manx",
//            "com.artemsirosh.tij.typeinfo.pets.Cymric",
//            "com.artemsirosh.tij.typeinfo.pets.Rat",
//            "com.artemsirosh.tij.typeinfo.pets.Mouse",
//            "com.artemsirosh.tij.typeinfo.pets.Hamster",
//            "com.artemsirosh.tij.typeinfo.pets.Gerbil"
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
