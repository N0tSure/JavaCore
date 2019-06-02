package com.artemsirosh.tij.arrays;

import com.artemsirosh.tij.util.Generator;

/**
 * Created by cresh on 07.02.17.
 */
public class BerilliumSphereGenerator implements Generator<BerylliumSphere> {
    @Override
    public BerylliumSphere next() {
        return new BerylliumSphere();
    }
}
