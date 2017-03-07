package ekkel.book.innerclasses.anoconst;

/**
 * Created by cresh on 11.06.16.
 */
public class AnonymousConstructor {
    public static Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("Initialisation exemplar.");
            }
            @Override
            public void f() {
                System.out.println("Anonymous f().");
            }
        };
    }

    public static void main(String[] args) {
        Base b = getBase(47);
        b.f();
    }
}
