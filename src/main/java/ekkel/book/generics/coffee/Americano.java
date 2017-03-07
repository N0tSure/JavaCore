//: generics/coffee/Americano.java
package ekkel.book.generics.coffee;
public class Americano extends Coffee {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Americano> {
        @Override
        public Americano create() {
            return new Americano();
        }
    }
} ///:~
