package com.artemsirosh.tij.typeinfo.toys;

/**
 * Created by cresh on 04.08.16.
 */
class Toyz {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        FancyToy ft = new FancyToy();
        ft.getParent(ft);
        char[] c = new char[]{'c','h','a','-','c','h','a','-','c','h','a'};
        Class clazz = char.class;
        System.out.println(c.getClass().isArray());
        System.out.println(clazz.isPrimitive());
    }
}
