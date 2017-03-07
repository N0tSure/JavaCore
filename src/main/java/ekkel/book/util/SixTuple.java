package ekkel.book.util;

/**
 * Created by cresh on 26.08.16.
 */
public class SixTuple<A,B,C,D,E,F> extends FiveTuple<A,B,C,D,E> {
    public final F sixth;

    public SixTuple(A first, B second, C third, D fourth, E fifth, F sixth) {
        super(first, second, third, fourth, fifth);
        this.sixth = sixth;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third +
                ", " + this.fourth + ", " + this.fifth + ", " + this.sixth + ")";
    }
}
