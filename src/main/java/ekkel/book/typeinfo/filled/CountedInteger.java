package ekkel.book.typeinfo.filled;

/**
 * Created by cresh on 09.08.16.
 */
class CountedInteger {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
