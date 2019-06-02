package com.artemsirosh.tij.io.cad;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 22 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Line extends Shape {

    private static Colors color = Colors.RED;

    public Line(int x, int y, int dimension) {
        super(x, y, dimension);
    }

    public static void serializeStaticState(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(color);
    }

    public static void deserializeStaticState(ObjectInputStream inputStream)
            throws IOException, ClassNotFoundException {

        Line.color = (Colors) inputStream.readObject();
    }

    @Override
    void setColor(Colors color) {
        Line.color = color;
    }

    @Override
    Colors getColor() {
        return Line.color;
    }
}
