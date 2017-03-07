package ekkel.book.typeinfo.shapes;

/**
 * Created by cresh on 03.08.16.
 */
class Circle extends Shape {
    Circle() {
        //no-op
    }

    Circle(int mark) {
        circleHeadlined = true;
    }
    @Override
    public String toString() {
        if (circleHeadlined) return this.getClass().getSimpleName() + ":marked";
        return "Circle";
    }
}
