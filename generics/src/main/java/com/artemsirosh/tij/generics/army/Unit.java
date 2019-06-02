package com.artemsirosh.tij.generics.army;

import com.artemsirosh.tij.generics.story.DiabloIIGenerarator;
import com.artemsirosh.tij.generics.story.Monsters;
import com.artemsirosh.tij.util.Generator;

import java.util.Random;

/**
 * Created by cresh on 29.08.16.
 */
class Unit extends Monsters {
    private String name;
    private float strength;

    private Unit(String name, float strength) {
        this.name = name;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Unit: " + this.id + " " + this.name + ": ready to order.";
    }

    public static Generator<Unit> generator() {
        return new Generator<Unit>() {
            private Generator<Monsters> monstersGenerator =
                    new DiabloIIGenerarator().monstersGenerator();
            private Random random = new Random(147);
            private Monsters monster;
            private float getStrenght() {
                return Math.round((random.nextFloat() * 1000.0f) + 0.99f);
            }

            @Override
            public Unit next() {
                monster = monstersGenerator.next();
                return new Unit(monster.getClass().getSimpleName(),this.getStrenght());
            }
        };
    }
}
