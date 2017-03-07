package ekkel.book.typeinfo.shapes;

/**
 * Created by cresh on 03.08.16.
 */
class Rhomboid extends Shape {
    Rhomboid() {
       //no-op
    }

    Rhomboid(int i) {
        rhomboidHeadlined = true;
    }
    @Override
    public String toString() {
        if (rhomboidHeadlined) return this.getClass().getSimpleName() + ":marked";
        return "Rhomboid";
    }
}
