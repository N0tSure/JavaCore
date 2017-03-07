package ekkel.book.generics;

/**
 * Created by cresh on 15.12.16.
 */
class BoundedImpl extends Bounded<BoundedImpl> {
    @Override
    BoundedImpl first(BoundedImpl bounded) {
        return bounded;
    }

    public static void main(String[] args) {
        BoundedImpl bounded = new BoundedImpl();
        System.out.println(bounded.first(new BoundedImpl()));
        System.out.println(bounded.derived(new BoundedImpl()));
    }
}
