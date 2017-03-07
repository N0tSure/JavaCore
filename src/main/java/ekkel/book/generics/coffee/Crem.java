package ekkel.book.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Crem extends Decorator {
    public Crem(Coffee coffee) {
        super(coffee);
    }

    String getTaste() {
        return "Taste of crem";
    };
}
