package com.artemsirosh.tij.generics.mixins;

import java.awt.Color;
/**
 * Created by cresh on 18.12.16.
 */
class Green implements Colored {
    private Color color;

    public Green() {
        this.color = Color.GREEN;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
