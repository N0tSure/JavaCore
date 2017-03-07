package ekkel.book.generics.coffee;

/**
 * Created by cresh on 18.12.16.
 */
class Decoration {
    public static void main(String[] args) {
        Foam foamed = new Foam(new Cappuccino());
        System.out.printf("%s with the %s\n", foamed, foamed.getOdour());

        Caramel carameled = new Caramel(new Chocolate(new Breve()));
        System.out.printf("%s with the %s\n", carameled, carameled.getSweet());
    }
}
