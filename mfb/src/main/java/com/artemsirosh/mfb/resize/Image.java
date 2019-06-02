package com.artemsirosh.mfb.resize;

/**
 * Created on 16 May, 2017.
 * demonstration of image
 * @author Artemis A. Sirosh
 */
public interface Image {

    char DARK = 'H';
    char LIGHT = '_';

    int getWidth();
    int getHeight();
    char getPixel(int x, int y);
    void setPixel(char pix, int x, int y);

}
