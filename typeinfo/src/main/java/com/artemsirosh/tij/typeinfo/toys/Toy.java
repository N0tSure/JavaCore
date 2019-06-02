package com.artemsirosh.tij.typeinfo.toys;

import java.lang.reflect.Field;

/**
 * Created by cresh on 03.08.16.
 */
class Toy {
    private String name;
    protected int price;
    protected int ageRate;
    Toy() {
        //no-op
    }

    Toy(int i) {}

    void getParent(Toy toy) throws InstantiationException, IllegalAccessException{
        Class clazz = this.getClass();
        if (clazz.getSuperclass().equals(Toy.class)) {
            Toy obj = (Toy) clazz.getSuperclass().newInstance();
            obj.getParent(obj);
        } else System.out.println("Старший объект: " + clazz.getSuperclass().getSimpleName());
        System.out.println(clazz.getSimpleName());
        System.out.println("Поля: ");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("Имя: %s, доступ %s\n",field.getName(),Integer.toBinaryString(field.getModifiers()));
        }
        System.out.println();
    }
}
