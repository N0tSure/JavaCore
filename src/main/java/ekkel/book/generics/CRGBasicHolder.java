package ekkel.book.generics;

/**
 * Created by cresh on 15.12.16.
 */
class CRGBasicHolder {
    public static void main(String[] args) {
        Subtype first = new Subtype();
        Subtype second = new Subtype();
        first.set(second);
        Subtype third = first.get();
        first.f();
    }
}
