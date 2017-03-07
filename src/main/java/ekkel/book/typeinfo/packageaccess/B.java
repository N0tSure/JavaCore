package ekkel.book.typeinfo.packageaccess;

/**
 * Created by cresh on 16.08.16.
 */
class B implements A {
    @Override
    public void f() {
        System.out.println("public B.f()");
    }
    public void g() {
        System.out.println("public B.g()");
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
}
