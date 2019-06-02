//: generics/coffee/Latte.java
package com.artemsirosh.tij.generics.coffee;
public class Latte extends Coffee {
    static class Factory implements com.artemsirosh.tij.typeinfo.factory.Factory<Latte> {
        @Override
        public Latte create() {
            return new Latte();
        }
    }
} ///:~
