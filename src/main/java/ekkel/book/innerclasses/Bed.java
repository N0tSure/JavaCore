package ekkel.book.innerclasses;

/**
 * Created by cresh on 01.07.16.
 */
class Bed {
    void f() {
        System.err.println("f()");
    }
    public static class Tester {
        public static void main(String[] args) {
            new Bed().f();
        }
    }
}
