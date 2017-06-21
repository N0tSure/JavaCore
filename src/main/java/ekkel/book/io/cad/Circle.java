package ekkel.book.io.cad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 22 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Circle extends Shape {

    private static Colors color = Colors.RED;

    public Circle(int x, int y, int dimension) {
        super(x, y, dimension);
    }

    public static void serializeStaticState(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(color);
    }

    public static void deserializeStaticState(ObjectInputStream inputStream)
            throws IOException, ClassNotFoundException {

        Circle.color = (Colors) inputStream.readObject();
    }

    @Override
    void setColor(Colors color) {
        Circle.color = color;
    }

    @Override
    Colors getColor() {
        return Circle.color;
    }
}
