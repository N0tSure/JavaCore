package ekkel.book.typeinfo;

import ekkel.book.generics.coffee.Coffee;
import ekkel.book.generics.coffee.CoffeeGenerator;

/**
 * Created by cresh on 10.08.16.
 */
class CoffeeCounter {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Coffee.class);

        for (Coffee coffee : new CoffeeGenerator(20)) {
            System.out.printf("%s ",coffee.getClass().getSimpleName());
            counter.count(coffee);
        }

        System.out.println();
        System.out.println(counter);
    }
}
