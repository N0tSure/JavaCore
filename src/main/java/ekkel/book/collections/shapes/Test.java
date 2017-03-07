package ekkel.book.collections.shapes;

/**
 * Created by cresh on 19.03.16.
 */
class Test {
    public static void main(String[] args) {
        RandomShapeGenerator generator = new RandomShapeGenerator(7);
        for (Shape shape : generator) {
            shape.draw();
        }
    }
}
