//: generics/coffee/Americano.java
package com.artemsirosh.tij.generics.coffee;
public class Americano extends Coffee {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Americano> {
        @Override
        public Americano create() {
            return new Americano();
        }
    }
} ///:~
