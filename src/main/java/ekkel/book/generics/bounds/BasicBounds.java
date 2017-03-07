package ekkel.book.generics.bounds;

/**
 * Created by cresh on 03.12.16.
 */
class BasicBounds {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());
        solid.color();
        solid.getZ();
        solid.weight();
    }
}
