//: generics/coffee/Mocha.java
package ekkel.book.generics.coffee;
public class Mocha extends Coffee {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Mocha> {
        @Override
        public Mocha create() {
            return new Mocha();
        }
    }
} ///:~
