package ekkel.book.util;

/**
 * Created by cresh on 26.08.16.
 */
public class FiveTuple<A,B,C,D,E> extends FourTuple<A,B,C,D> {
    public final E fifth;

    public FiveTuple(A first, B second, C third, D fourth, E fifth) {
        super(first, second, third, fourth);
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "(" + this.first + ", " + this.second + ", " + this.third +
                ", " + this.fourth + ", " + this.fifth + ")";
    }
}
