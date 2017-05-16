package shield.book.resize;

/**
 * Created on 16 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class Resize {

    public static void main(String[] args) {
        Image bordered = new Bordered(12, 12, 3);
        System.out.println(bordered);
    }

    private static void resizeImage(Image image, float f) {
        int resultHeight = Math.round(image.getHeight() * f);
        int resultWidth = Math.round(image.getWidth() * f);

        for (int i = 0; i < resultWidth; i++) {
            for (int j = 0; j < resultHeight; j++) {

            }
        }
    }
}
