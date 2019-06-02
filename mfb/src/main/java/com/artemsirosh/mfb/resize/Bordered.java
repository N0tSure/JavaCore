package com.artemsirosh.mfb.resize;

/**
 * Created on 16 May, 2017.
 * Parallelogram with inner unfilled plane
 * @author Artemis A. Sirosh
 */
class Bordered extends AbstractImage {

    public Bordered(int width, int height) {
        super(width, height);
    }

    public Bordered(int width, int height, int wall) {
        super(width, height);

        if (wall >= width || wall >= height)
            throw new IllegalArgumentException("Wall cannot be wider than square");

        body = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean isHole = ((i >= wall && j >= wall) && (i < width - wall && j < height - wall));
                body[i][j] = isHole ? Image.LIGHT : Image.DARK;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getPixel(int x, int y) {
        return body[x][y];
    }

    @Override
    public void setPixel(char pix, int x, int y) {
        body[x][y] = pix;
    }

}
