package com.artemsirosh.tij.typeinfo;

import com.artemsirosh.tij.typeinfo.packageaccess.A;

/**
 * Created by cresh on 25.08.16.
 */
class AnonymousImplementation {
    public static A makeA() {
        return new A() {
            @Override
            public void f() {
                System.out.println("public C.f()");
            }

            public void g() {
                System.out.println("public C.g()");
            }

            void u() {
                System.out.println("package C.u()");
            }

            protected void v() {
                System.out.println("protected C.v()");
            }

            private void w() {
                System.out.println("private C.w()");
            }
        };
    }
}
