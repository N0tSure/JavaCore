package ekkel.book.generics.latency;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 19.12.16.
 */
public class ApplyTest {

    @Test
    public void test() throws Exception {
        List<Shape> shapes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            shapes.add(new Shape());
        }
        Apply.apply(shapes, Shape.class.getMethod("rotate"));
        Apply.apply(shapes, Shape.class.getMethod("resize", int.class), 5);

        List<Square> squares = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            squares.add(new Square());
        }
        Apply.apply(squares, Shape.class.getMethod("rotate"));
        Apply.apply(squares, Shape.class.getMethod("resize", int.class), 5);

        Apply.apply(new FilledList<Shape>(Shape.class, 10),Shape.class.getMethod("rotate"));
        Apply.apply(new FilledList<Shape>(Square.class, 10),Square.class.getMethod("rotate"));

        SimpleQueue<Shape> queue = new SimpleQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.add(new Shape());
            queue.add(new Square());
        }
        Apply.apply(queue, Shape.class.getMethod("rotate"));
    }

}
