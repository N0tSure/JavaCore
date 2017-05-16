package shield.book.resize;

/**
 * Created on 16 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
class Bordered implements Image {

    private static final char DARK = 'H';
    private static final char LIGHT = '_';

    private int width;
    private int height;
    private char[][] body;

    public Bordered(int width, int height, int wall) {
        this.width = width;
        this.height = height;

        if (wall >= width || wall >= height)
            throw new IllegalArgumentException("Wall cannot be wider than square");

        body = new char[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean isHole = ((i >= wall && j >= wall) && (i < width - wall && j < height - wall));
                body[i][j] = isHole ? Bordered.LIGHT : Bordered.DARK;
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
