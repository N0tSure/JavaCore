package ekkel.book.io.cad;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 22 Jun, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class CADStateStoringTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CADStateStoringTest.class);

    private File systemState = new File("state.dat");

    @Test
    public void storingTest() throws Exception {

        List<Shape> shapes = Stream.generate(Shape.randomFactory())
                .peek(shape -> shape.setColor(Shape.Colors.GREEN))
                .limit(10)
                .collect(Collectors.toList());

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(systemState))) {

            Line.serializeStaticState(outputStream);
            Circle.serializeStaticState(outputStream);
            Square.serializeStaticState(outputStream);
            outputStream.writeObject(shapes);
        }

        LOGGER.info("Into file {}, written {} bytes", systemState, systemState.length());
        LOGGER.info("Saved shapes:");
        for (Shape shape : shapes) {
            LOGGER.info("{}", shape);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void recoveringTest() throws Exception {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(systemState))){

            Line.deserializeStaticState(inputStream);
            Circle.deserializeStaticState(inputStream);
            Square.deserializeStaticState(inputStream);

            List<Shape> shapes = (List<Shape>) inputStream.readObject();

            LOGGER.info("Recovered:");
            for (Shape shape : shapes) {
                LOGGER.info("{}", shape);
            }
        }
    }
}
