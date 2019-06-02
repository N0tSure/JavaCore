//: generics/coffee/Cappuccino.java
package com.artemsirosh.tij.generics.coffee;
public class Cappuccino extends Coffee {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Cappuccino> {
        @Override
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
} ///:~
