package ekkel.book.generics;

import ekkel.book.generics.coffee.Cappuccino;
import ekkel.book.generics.coffee.Coffee;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cresh on 01.12.16.
 */
public class TypeCaptureTest {
    @Test
    public void test() {
        ClassTypeCapture capture = new ClassTypeCapture();
        capture.addType("Coffee", Coffee.class);
        capture.addType("Cappuccino", Cappuccino.class);

        Object coffee = capture.createNew("Coffee");
        System.out.println(coffee);
        Assert.assertNotNull(coffee);
        Assert.assertTrue(coffee instanceof Coffee);

        coffee = capture.createNew("Cappuccino");
        System.out.println(coffee);
        Assert.assertNotNull(coffee);
        Assert.assertTrue(coffee instanceof Coffee);
        Assert.assertTrue(coffee instanceof Cappuccino);

        coffee = capture.createNew("Breve");
        System.out.println(coffee);
        Assert.assertNull(coffee);
    }
}
