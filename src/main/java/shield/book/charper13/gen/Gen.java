package shield.book.charper13.gen;

/**
 * Created by cresh on 11.04.16.
 */
class Gen<T> {
    //static T t1;
    private T t;

    Gen(T t) {
        this.t=t;
        //t = new T();
    }

    T getT() {
        return t;
    }


    void showType() {
        System.out.println("Type T is " + t.getClass().getName());
    }
}
