package com.artemsirosh.tij.typeinfo;

import java.lang.reflect.Field;

/**
 * Created by cresh on 25.08.16.
 */
class ModifyingPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFinalField privateFinalField = new WithPrivateFinalField();
        System.out.println(privateFinalField);
        Field field = privateFinalField.getClass().getDeclaredField("i");
        field.setAccessible(true);
        System.out.println("field.getInt(privateFinalField): " + field.getInt(privateFinalField));
        field.setInt(privateFinalField,47);
        System.out.println(privateFinalField);
        field = privateFinalField.getClass().getDeclaredField("string");
        field.setAccessible(true);
        System.out.println("field.get(privateFinalField): " + field.get(privateFinalField));
        field.set(privateFinalField,"No, you're not!");
        System.out.println(privateFinalField);
        field = privateFinalField.getClass().getDeclaredField("string2");
        field.setAccessible(true);
        System.out.println("field.get(privateFinalField): " + field.get(privateFinalField));
        field.set(privateFinalField,"No, you're not!");
        System.out.println(privateFinalField);
    }
}
