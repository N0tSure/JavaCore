package com.artemsirosh.tij.generics.story;

import com.artemsirosh.tij.util.Generator;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by cresh on 26.08.16.
 */
public class DiabloIIGenerarator implements Generator<StoryCharacter>,Iterable<StoryCharacter> {
    private Class[] characters = { Adria.class, Andariel.class, Baal.class, DekardKain.class,
    Diablo.class, Duriel.class, Jerhyn.class, Mephisto.class, Tyrael.class };

    private Class[] monsters = { BloodHawk.class, ClawViper.class, Fallen.class, Goatman.class,
    Mummy.class, Skeleton.class };

    private Random random;
    private int count;

    public DiabloIIGenerarator() {
        this.random = new Random(47);
        this.count = 0;
    }

    public DiabloIIGenerarator(int count) {
        this.random = new Random(47);
        this.count = count;
    }

    @Override
    public StoryCharacter next() {
        try {
            return (StoryCharacter) characters[random.nextInt(characters.length)].newInstance();
        } catch (InstantiationException | IllegalAccessException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Iterator<StoryCharacter> iterator() {
        return new Iterator<StoryCharacter>() {
            int index = count;
            @Override
            public boolean hasNext() {
                return index > 0;
            }

            @Override
            public StoryCharacter next() {
                index--;
                return DiabloIIGenerarator.this.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Generator<Monsters> monstersGenerator() {
        return new Generator<Monsters>() {
            @Override
            public Monsters next() {
                try {
                    return (Monsters) monsters[random.nextInt(monsters.length)].newInstance();
                } catch (InstantiationException | IllegalAccessException exc) {
                    throw new RuntimeException(exc);
                }
            }
        };
    }

    public static void main(String[] args) {
        DiabloIIGenerarator generator = new DiabloIIGenerarator();
        for (int i = 0; i < 18; i++) {
            System.out.println(generator.next());
        }

        System.out.println();

        for (StoryCharacter character : new DiabloIIGenerarator(18)) {
            System.out.println(character);
        }
    }
}
