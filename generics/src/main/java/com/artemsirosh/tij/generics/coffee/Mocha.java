//: generics/coffee/Mocha.java
package com.artemsirosh.tij.generics.coffee;
public class Mocha extends Coffee {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Mocha> {
        @Override
        public Mocha create() {
            return new Mocha();
        }
    }
} ///:~
