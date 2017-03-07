package ekkel.book.typeinfo.select;

/**
 * Created by cresh on 15.08.16.
 */
class Implementation implements SomeMethods {
    @Override
    public void aLittleBitBoring() {
        System.out.println("A Little Bit boring method.");
    }

    @Override
    public void mainlyBoring() {
        System.out.println("Mainly boring method.");
    }

    @Override
    public void interesting(String argument) {
        System.out.println("interesting " + argument);
    }

    @Override
    public void veryBoring() {
        System.out.println("Boring very match method.");
    }
}
