package ekkel.book.typeinfo.shapes;

/**
 * Created by cresh on 03.08.16.
 */
class Triangle extends Shape {
    Triangle() {
        //no-op
    }

    Triangle(int i) {
        triangleHeadlined = true;
    }
    @Override
    public String toString() {
        if (triangleHeadlined) return this.getClass().getSimpleName() + ":marked";
        return "Triangle";
    }
}
