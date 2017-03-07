package ekkel.book.generics.mixins;

/**
 * Created by cresh on 17.12.16.
 */
class Mixins {
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        System.out.println(mixin1.get() + " " +
                mixin1.getStamp() +  " " + mixin1.getSerialNumber() + " " + mixin1.getColor());
        System.out.println(mixin2.get() + " " +
                mixin2.getStamp() +  " " + mixin2.getSerialNumber() + " " + mixin2.getColor());
    }
}
