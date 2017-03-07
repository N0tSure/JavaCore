package ekkel.book.collections.shapes;

/**
 * Created by cresh on 19.03.16.
 */
public class Square extends Shape {
    @Override
    public void draw() {
        System.out.println("The Square was drew!");
    }

    @Override
    public void erase() {
        System.out.println("The Square was erased!");
    }
}
