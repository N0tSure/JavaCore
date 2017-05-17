package shield.book.resize;

import static java.lang.Math.round;
import static java.lang.Math.floor;

/**
 * Created on 16 May, 2017.
 *
 * @author Artemis A. Sirosh
 */
public class Resize {

    public static void main(String[] args) {
        Image bordered = new Bordered(12, 12, 4);
        System.out.println("Original \n" + bordered);

        System.out.println("Reduced in 2 times\n" + resizeImage(bordered,.5f));
        System.out.println("Reduced in 4 times\n" + resizeImage(bordered,.25f));
        System.out.println("Reduced in 10 times\n" + resizeImage(bordered,.2f));


        Image small = new Bordered(3, 3, 1);
        System.out.println("Small\n" + small);

        System.out.println("Increased in 2 times\n" + resizeImage(small, 2f));
        System.out.println("Increased in 4 times\n" + resizeImage(small, 4f));
        System.out.println("Increased in 10 times\n" + resizeImage(small, 5.5f));

    }

    private static Image resizeImage(Image image, float f) {
        int resultHeight = round(image.getHeight() * f);
        int resultWidth = round(image.getWidth() * f);
        Image result = new Bordered(resultWidth, resultHeight);

        for (int i = 0; i < resultWidth; i++) {
            for (int j = 0; j < resultHeight; j++) {
                char p = image.getPixel((int) floor(i / f), (int) floor(j / f));
                result.setPixel(p, i, j);
            }
        }

        return result;
    }
}
