package ekkel.book.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Chocolate extends Decorator {
    public Chocolate(Coffee coffee) {
        super(coffee);
    }

    String getSweetAndTaste() {
        return "Sweet and taste of the Chocolate";
    };
}
