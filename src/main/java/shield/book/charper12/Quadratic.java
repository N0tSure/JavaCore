package shield.book.charper12;

import static java.lang.Math.*;
/**
 * Created by cresh on 04.04.16.
 */
class Quadratic {
    public static void main(String[] args) {
        double a, b, c, x;

        a=4;
        b=1;
        c=-3;

        x=(-b+sqrt(pow(b, 2) - 4 * a * c))/(2*a);
        System.out.println("First root: " + x);

        x=(-b-sqrt(pow(b, 2) - 4 * a * c))/(2*a);
        System.out.println("Second root: " + x);
        method();
    }

    @Deprecated
    @MyAnno(val = "MyAnno",num = 0)
    public static void method() {}
}
