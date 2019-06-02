//: generics/coffee/Breve.java
package com.artemsirosh.tij.generics.coffee;
public class Breve extends Coffee {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Breve> {
        @Override
        public Breve create() {
            return new Breve();
        }
    }
} ///:~
