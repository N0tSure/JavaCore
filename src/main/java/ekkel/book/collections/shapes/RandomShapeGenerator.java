package ekkel.book.collections.shapes;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by cresh on 19.03.16.
 */
public class RandomShapeGenerator implements Iterable<Shape> {
    private int index;
    private Random rnd;

    public RandomShapeGenerator(int index) {
        this.index = index;
        rnd = new Random();
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            @Override
            public boolean hasNext() {
                return index>=0;
            }

            @Override
            public Shape next() {
                index--;
                switch (rnd.nextInt(3)) {
                    case 0: return new Circle();
                    case 1: return new Triangle();
                    case 2: return new Square();
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
