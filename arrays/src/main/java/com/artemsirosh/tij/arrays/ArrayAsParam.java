package com.artemsirosh.tij.arrays;

import java.util.Arrays;

/**
 * Created by cresh on 24.01.17.
 */
public class ArrayAsParam {
    private static void a(BerylliumSphere[] spheres) {
        System.out.println(Arrays.toString(spheres));
    }

    private static void b(BerylliumSphere... spheres) {
        System.out.println(Arrays.toString(spheres));
    }

    public static void main(String[] args) {
        a(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()});
        b(new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere());
    }
}
