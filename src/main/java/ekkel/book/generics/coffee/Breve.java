//: generics/coffee/Breve.java
package ekkel.book.generics.coffee;
public class Breve extends Coffee {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Breve> {
        @Override
        public Breve create() {
            return new Breve();
        }
    }
} ///:~
