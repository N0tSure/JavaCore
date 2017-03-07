package ekkel.book.innerclasses;

/**
 * Created by cresh on 30.05.16.
 */
class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }
    class Inner {
        DotThis outer() {
            return DotThis.this;
        }
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.new Inner();
        dti.outer().f();
        System.out.println(dt.equals(dti.outer()));

        Content content = (new Outer(12)).content();
        //CInner inner = (CInner) content;

    }
}
