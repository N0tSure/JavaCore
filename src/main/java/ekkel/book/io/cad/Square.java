package ekkel.book.io.cad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 22 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Square extends Shape {

    private static Colors color;

    public Square(int x, int y, int dimension) {
        super(x, y, dimension);
        color = Colors.RED;
    }

    public static void serializeStaticState(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(color);
    }

    public static void deserializeStaticState(ObjectInputStream inputStream)
            throws IOException, ClassNotFoundException {

        Square.color = (Colors) inputStream.readObject();
    }

    @Override
    void setColor(Colors color) {
        Square.color = color;
    }

    @Override
    Colors getColor() {
        return Square.color;
    }
}
