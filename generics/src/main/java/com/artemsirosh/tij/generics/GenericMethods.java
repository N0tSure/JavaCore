package com.artemsirosh.tij.generics;

/**
 * Created by cresh on 26.08.16.
 */
class GenericMethods {
    public <A,B,C> void f(A a, B b, C c) {
        System.out.printf("A: %-15s B: %-15s C: %-15s\n",
                a.getClass().getSimpleName(), b.getClass().getSimpleName(),
                c.getClass().getSimpleName());
    }

    public <A,B,C> void f(A a, B b, Short c) {
        System.out.printf("A: %-15s B: %-15s C: %-15s\n",
                a.getClass().getSimpleName(), b.getClass().getSimpleName(),
                c.getClass().getSimpleName());
    }

    public <T> void f(T t) {
        System.out.println(t.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        GenericMethods methods = new GenericMethods();

        methods.f("",1,3.14159265359);
        methods.f(3.14159265359F,4276855032892164L,(short)1000);
        methods.f((byte)356,'C',methods);

    }
}
