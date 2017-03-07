package ekkel.book.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Caramel extends Decorator {
    public Caramel(Coffee coffee) {
        super(coffee);
    }

    String getSweet() {
        return "Sweet of caramel";
    }
}
