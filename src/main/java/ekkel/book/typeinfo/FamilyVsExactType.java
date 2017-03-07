package ekkel.book.typeinfo;

import ekkel.book.generics.coffee.Coffee;
import ekkel.book.generics.coffee.Mocha;

/**
 * Created by cresh on 10.08.16.
 */
class FamilyVsExactType {
    static void test(Object o) {
        System.out.printf("Тестирование о типа: %s\n",o.getClass());
        System.out.printf("o instanceof Coffee: %b\n",o instanceof Coffee);
        System.out.printf("o instanceof Mocha: %b\n",o instanceof Mocha);
        System.out.printf("Coffee.class.isInstance(o): %b\n",
                Coffee.class.isInstance(o));
        System.out.printf("Mocha.class.isInstance(o): %b\n",
                Mocha.class.isInstance(o));
        System.out.printf("Coffee.class==o.getClass(): %b\n", Coffee.class==o.getClass());
        System.out.printf("Mocha.class==o.getClass(): %b\n", Mocha.class==o.getClass());
        System.out.printf("o.getClass().equals(Coffee.class): %b\n",
                o.getClass().equals(Coffee.class));
        System.out.printf("o.getClass().equals(Mocha.class): %b\n\n",
                o.getClass().equals(Mocha.class));
    }

    public static void main(String[] args) {
        test(new Coffee());
        test(new Mocha());
    }
}
