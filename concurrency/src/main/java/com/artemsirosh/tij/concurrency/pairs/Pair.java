package com.artemsirosh.tij.concurrency.pairs;

import java.util.Objects;

/**
 * Created at 09-06-2019
 *
 * Simple pairs. x and y values must be equal.
 * Not thread safe.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class Pair {

    private int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair pair) {
        this(pair.x, pair.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Increments X value.
     */
    public void incrementX() {
        x++;
    }

    /**
     * Increments Y value.
     */
    public void incrementY() {
        y++;
    }

    /**
     * Check inner state of pairs.
     * @throws PairValueNotEqualException if pairs values not equal
     */
    public void checkState() {
        if (this.x != this.y)
            throw new PairValueNotEqualException(this);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Pair pair = (Pair) that;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Pair{x=" + x + ", y=" + y + '}';
    }
}
