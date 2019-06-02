package com.artemsirosh.tij.typeinfo.shapes;

/**
 * Created by cresh on 03.08.16.
 */
abstract class Shape {
    protected boolean circleHeadlined;
    protected boolean squareHeadlined;
    protected boolean triangleHeadlined;
    protected boolean rhomboidHeadlined;

    protected Shape() {
        this.circleHeadlined = false;
        this.squareHeadlined = false;
        this.triangleHeadlined = false;
        this.rhomboidHeadlined = false;
    }
    void draw() {
        System.out.printf("%s.draw()\n",this.toString());
    }

    void rotate() {
        if (!(this instanceof Circle)) {
            System.out.printf("%s.rotate()\n",this.toString());
        }
    }

    @Override
    public abstract String toString();
}
