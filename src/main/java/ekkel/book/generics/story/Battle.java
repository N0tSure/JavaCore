package ekkel.book.generics.story;

import ekkel.book.util.Generator;

import java.util.*;

/**
 * Created by cresh on 28.08.16.
 */
class Battle {
    static void kill(GoodGuys good, Monsters monster) {
        System.out.printf("%s smashed this %s\n",good,monster);
    }
    public static void main(String[] args) {
        Queue<Monsters> crownd = new LinkedList<>();
        DiabloIIGenerarator generator = new DiabloIIGenerarator();
        Generator<Monsters> monsters = generator.monstersGenerator();
        StoryCharacter c;

        for (int i = 0; i < 15; i++) {
            crownd.add(monsters.next());
        }

        for (Monsters monster : crownd) {
            c = generator.next();
            while (!(c instanceof GoodGuys))
                c = generator.next();
            kill((GoodGuys) c,monster);
        }
    }
}
