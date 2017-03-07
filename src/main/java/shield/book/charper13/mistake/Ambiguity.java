package shield.book.charper13.mistake;

/**
 * Created by cresh on 21.04.16.
 */
class Ambiguity<T, V> {
    T t;
    V v;
/*
    void set(T t) {
        this.t=t;
    }
*/
    void set(V v) {
        this.v=v;
    }
}
