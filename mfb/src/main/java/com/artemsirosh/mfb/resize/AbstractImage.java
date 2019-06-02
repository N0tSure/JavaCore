package com.artemsirosh.mfb.resize;

/**
 * Created on 17.05.2017.
 * Basic realisation of image
 * @author Artemis A. Sirosh
 */
public abstract class AbstractImage implements Image {

    protected int width;
    protected int height;
    protected char[][] body;

    public AbstractImage(int width, int height) {
        this.width = width;
        this.height = height;

        this.body = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.body[i][j] = Image.LIGHT;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Size: %dx%d\n", width, height));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                builder.append(body[i][j]);
                builder.append(' ');
            }
            builder.append('\n');
        }

        return builder.toString();
    }
}
