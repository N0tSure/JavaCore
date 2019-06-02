package com.artemsirosh.mfb.charper13.bounded.boundedargs;

/**
 * Created by cresh on 11.04.16.
 */
class UseBoundedWildCards {
    static void test(Gen<? extends A> gen) {}

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        D d = new D();

        Gen<A> w = new Gen<A>(a);
        Gen<B> w1 = new Gen<B>(b);
        Gen<C> w2 = new Gen<C>(c);
        Gen<D> w3 = new Gen<D>(d);

        test(w);
        test(w1);
        test(w2);
        //test(w3);
    }
}
