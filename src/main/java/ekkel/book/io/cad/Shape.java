package ekkel.book.io.cad;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Random;
import java.util.function.Supplier;

/**
 * Created on 22 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
abstract class Shape implements Serializable {

    private static final Random RANDOM = new Random(42);
    private static int counter = 0;

    private int x, y, dimension;

    public Shape(int x, int y, int dimension) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
    }

    static Supplier<Shape> randomFactory() {
        return () -> {
            int x = RANDOM.nextInt(100);
            int y = RANDOM.nextInt(100);
            int dim = RANDOM.nextInt(100);

            switch (counter++ % 3) {
                default:
                case 0:
                    return new Circle(x, y, dim);
                case 1:
                    return new Square(x, y, dim);
                case 2:
                    return new Line(x, y, dim);

            }
        };
    }

    abstract void setColor(Shape.Colors color);
    abstract Shape.Colors getColor();

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("color", getColor())
                .add("x position", x)
                .add("y position", y)
                .add("dimension", dimension)
                .toString();
    }

    enum Colors {
        RED, GREEN, BLUE
    }
}
