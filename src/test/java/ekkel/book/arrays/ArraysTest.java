package ekkel.book.arrays;

import ekkel.book.util.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by cresh on 06.02.17.
 */
public class ArraysTest {
    @Test
    public void test() {
        Integer[] a = { 9, 8, 7, 6 };
        System.out.println(Arrays.toString(a));
        a = Generated.toArray(a, CountingGenerator.getIntegerGenerator());
        System.out.println(Arrays.toString(a));
        Integer[] b = Generated.toArray(Integer.class,
                CountingGenerator.getIntegerGenerator(), 15);
        System.out.println(Arrays.toString(b));
    }

    @Test
    public void primitiveConventionsTest() {
        Integer[] a = Generated.toArray(Integer.class, CountingGenerator.getIntegerGenerator(), 15);
        int[] b = Converter.primitive(a);
        System.out.println(Arrays.toString(b));
        boolean[] c = Converter.primitive(Generated.toArray(Boolean.class, CountingGenerator.getBooleanGenerator(), 7));
        System.out.println(Arrays.toString(c));
    }

    @Test
    public void arrayGenerationTest() {
        int size = 6;

        boolean[] a1 = Converter.primitive(Generated.toArray(Boolean.class, CountingGenerator.getBooleanGenerator(), size));
        System.out.println("a1 = " + Arrays.toString(a1));

        byte[] a2 = Converter.primitive(Generated.toArray(Byte.class, CountingGenerator.getByteGenerator(), size));
        System.out.println("a2 = " + Arrays.toString(a2));

        char[] a3 = Converter.primitive(Generated.toArray(Character.class, CountingGenerator.getCharacterGenerator(), size));
        System.out.println("a3 = " + Arrays.toString(a3));

        short[] a4 = Converter.primitive(Generated.toArray(Short.class, CountingGenerator.getShortGenerator(), size));
        System.out.println("a4 = " + Arrays.toString(a4));

        int[] a5 = Converter.primitive(Generated.toArray(Integer.class, CountingGenerator.getIntegerGenerator(), size));
        System.out.println("a5 = " + Arrays.toString(a5));

        long[] a6 = Converter.primitive(Generated.toArray(Long.class, CountingGenerator.getLongGenerator(), size));
        System.out.println("a6 = " + Arrays.toString(a6));

        float[] a7 = Converter.primitive(Generated.toArray(Float.class, CountingGenerator.getFloatGenerator(), size));
        System.out.println("a7 = " + Arrays.toString(a7));

        double[] a8 = Converter.primitive(Generated.toArray(Double.class, CountingGenerator.getDoubleGenerator(), size));
        System.out.println("a8 = " + Arrays.toString(a8));
    }

    @Test
    public void countingGenerator() {
        double[] a = new double[10];
        Generator<Double> doubleGenerator = CountingGenerator.getDoubleGenerator();
        for (int i = 0; i < a.length; i++) {
            a[i] = doubleGenerator.next();
        }
        System.out.println(Arrays.toString(a));

        String b = new String(
                Converter.primitive(Generated.toArray(Character.class, RandomGenerator.getCharacterGenerator(), 10)));
        System.out.println(b);
    }

    @Test
    public void bigDecimalGeneratorTest() {
        BigDecimal[] a = Generated.toArray(BigDecimal.class, new BigDecimalGenerator(), 10);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void berylliumGeneratorTest() {
        BerylliumSphere[] spheres = Generated.toArray(BerylliumSphere.class, new BerilliumSphereGenerator(), 8);
        BerylliumSphere[] copy = new BerylliumSphere[spheres.length];
        System.arraycopy(spheres, 0, copy, 0, spheres.length);
        System.out.println("Original: " + Arrays.toString(spheres));
        System.out.println("Copies: " + Arrays.toString(copy));

        Arrays.sort(copy);
        System.out.println("Original: " + Arrays.toString(spheres));
        System.out.println("Copies: " + Arrays.toString(copy));
    }

    @Test
    public void digitsTest() {
        Digit[] a = new Digit[10];
        Digit[] b = new Digit[10];
        Arrays.fill(a, new Digit(5));
        Arrays.fill(b, new Digit(5));
        System.out.println(Arrays.equals(a, b));

        for (int i = 0; i < 10; i++) {
            b[i] = new Digit((int) Math.round(Math.random() * 1000_000_000));
        }
        Arrays.sort(b);
        int index = -1;
        do {
           index = Arrays.binarySearch(b, new Digit((int) Math.round(Math.random() * 1000_000_000)));
        } while (index < 0);
        System.out.println("Result: " + index + ", " + b[index]);
    }

    @Test
    public void deepEqualsTest() {
        double[][] a = Dimension.twoDimension(3, 4, 0.2, 0.8);
        double[][] b = new double[3][4];
        for (int i = 0; i < a.length; i++) {
            System.arraycopy(a[i], 0, b[i], 0, a[i].length);
        }
        System.out.println("Before: ");
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
        System.out.println("Equals: " + Arrays.deepEquals(a, b));

        b[1][2] = 12.0;

        System.out.println("After: ");
        System.out.println(Arrays.deepToString(a));
        System.out.println(Arrays.deepToString(b));
        System.out.println("Equals: " + Arrays.deepEquals(a, b));
    }

}
