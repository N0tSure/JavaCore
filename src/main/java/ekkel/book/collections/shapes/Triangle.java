package ekkel.book.collections.shapes;

/**
 * Created by cresh on 19.03.16.
 */
public class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("The Triangle was drew!");
    }

    @Override
    public void erase() {
        System.out.println("The Triangle was erased!");
    }
}
