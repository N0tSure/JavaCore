package ekkel.book.innerclasses;

/**
 * Created by cresh on 01.07.16.
 */
interface ClassInInterface {
    void howdy();
    class Next {
        static void f(ClassInInterface c) {
            c.howdy();
        }
    }
    class Test implements ClassInInterface {
        @Override
        public void howdy() {
            System.err.println("Hello!");
        }

        public static void main(String[] args) {
            Next.f(new Test());
        }
    }
}
