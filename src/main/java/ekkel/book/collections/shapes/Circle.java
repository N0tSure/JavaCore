package ekkel.book.collections.shapes;

/**
 * Created by cresh on 19.03.16.
 */
public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("The Circle was drew!");
    }

    @Override
    public void erase() {
        System.out.println("The Circle was erased!");
    }
}
