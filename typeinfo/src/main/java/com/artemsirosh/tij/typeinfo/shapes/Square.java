package com.artemsirosh.tij.typeinfo.shapes;

/**
 * Created by cresh on 03.08.16.
 */
class Square extends Shape {
    Square() {
        //no-op
    }

    Square(int i) {
        squareHeadlined=true;
    }
    @Override
    public String toString() {
        if (squareHeadlined) return this.getClass().getSimpleName() + ":marked";
        return "Square";
    }
}
