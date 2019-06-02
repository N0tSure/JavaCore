package com.artemsirosh.tij.typeinfo.shapes;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cresh on 03.08.16.
 */
class Shapes {
    static void method(Shape shape) {
        if (shape instanceof Circle) System.out.println("круг");
        else if (shape instanceof Square) System.out.println("квадрат");
        else if (shape instanceof Triangle) System.out.println("треугольник");
        else if (shape instanceof Rhomboid) System.out.println("ромб");
    }
    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(
                new Circle(),new Square(),new Triangle(),new Rhomboid(1));
        for (Shape shape : shapes) {
            shape.draw();
            shape.rotate();
            method(shape);
        }
    }
}
