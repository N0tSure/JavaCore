package ekkel.book.arrays;


/**
 * Created by cresh on 24.01.17.
 */
public class BerylliumSphere implements Comparable<BerylliumSphere> {
    private static long counter;
    private final long id = counter++;

    @Override
    public int compareTo(BerylliumSphere o) {
        return Long.compare(o.id, this.id);
    }

    @Override
    public String toString() { return "Sphere " + id; }
}
