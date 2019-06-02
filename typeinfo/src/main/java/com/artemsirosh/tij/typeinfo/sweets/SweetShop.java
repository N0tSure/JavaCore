package com.artemsirosh.tij.typeinfo.sweets;

/**
 * Created by cresh on 03.08.16.
 */

public class SweetShop {
    private static Class classLoader(String nameClazz) throws ClassNotFoundException{
        return Class.forName(nameClazz);
    }
    public static void main(String[] args) {

        if (args.length>0) {
            for (String arg : args) {
                try {
                    System.out.println(classLoader(arg).getName());
                } catch (ClassNotFoundException exc) {
                    System.out.println("Не удалось найти " + arg);
                }
            }
        }
    }
}
