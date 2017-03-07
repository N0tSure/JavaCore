package ekkel.book.innerclasses;

/**
 * Created by cresh on 01.07.16.
 */
class MultiNestingAccsess {
    private void f() {

    }
    class A {
        private void g() {}
        public class B {
            void h() {
                f();
                g();
            }
        }
    }

    public static void main(String[] args) {
        MultiNestingAccsess m = new MultiNestingAccsess();
        MultiNestingAccsess.A ma = m.new A();
        MultiNestingAccsess.A.B mab = ma.new B();
        mab.h();
    }
}
