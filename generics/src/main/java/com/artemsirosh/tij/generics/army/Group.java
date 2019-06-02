package com.artemsirosh.tij.generics.army;

import com.artemsirosh.tij.generics.Generators;

import java.util.ArrayList;

/**
 * Created by cresh on 29.08.16.
 */
class Group extends ArrayList<Unit> {
    Group(int amounOfUnits) {
        Generators.fill(this,Unit.generator(),amounOfUnits);
    }
}
