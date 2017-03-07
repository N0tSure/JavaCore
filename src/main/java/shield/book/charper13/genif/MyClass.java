package shield.book.charper13.genif;

/**
 * Created by cresh on 15.04.16.
 */
class MyClass<T> implements Containment<T> {
    private T[] ts;
    public MyClass(T[] ts) {
        this.ts=ts;
    }

    @Override
    public boolean contains(T t) {
        for (T t1 : ts) {
          if (t.equals(t1)) return true;
        }
        return false;
    }
}
