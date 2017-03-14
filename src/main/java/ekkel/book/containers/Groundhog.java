package ekkel.book.containers;

/**
 * Created by cresh on 14.03.17.
 */
class Groundhog {
    private int number;

    Groundhog(int number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Groundhog && (this.number == ((Groundhog) obj).number);
    }

    @Override
    public String toString() {
        return "Groundhog #" + number;
    }
}
