package com.artemsirosh.tij.generics;

import com.artemsirosh.tij.generics.coffee.Coffee;
import com.artemsirosh.tij.generics.coffee.CoffeeGenerator;
import com.artemsirosh.tij.generics.story.DiabloIIGenerarator;
import com.artemsirosh.tij.generics.story.StoryCharacter;
import com.artemsirosh.tij.util.Generator;

import java.util.*;

/**
 * Created by cresh on 27.08.16.
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> collection, Generator<T> generator, int amount) {
        for (int i = 0; i < amount; i++) {
            collection.add(generator.next());
        }
        return collection;
    }

    public static <T> List<T> fill(List<T> list, Generator<T> generator, int amount) {
        for (int i = 0; i < amount; i++) {
            list.add(generator.next());
        }
        return list;
    }

    public static <T> Set<T> fill(Set<T> set, Generator<T> generator, int amount) {
        for (int i = 0; i < amount; i++) {
            set.add(generator.next());
        }
        return set;
    }

    public static <T> Queue<T> fill(LinkedList<T> queue, Generator<T> generator, int amount) {
        for (int i = 0; i < amount; i++) {
            queue.add(generator.next());
        }
        return queue;
    }


    public static void main(String[] args) {
        Collection<Coffee> coffees =
                fill(new HashSet<Coffee>(),new CoffeeGenerator(),4);
        for (Coffee coffee : coffees) System.out.println(coffee);

        Collection<StoryCharacter> diabloII =
                fill(new LinkedList<StoryCharacter>(), new DiabloIIGenerarator(),8);
        for (StoryCharacter character : diabloII) System.out.println(character);

        Collection<Integer> fibonacci = fill(new ArrayList<Integer>(), new Fibonacci(),15);
        for (int i : fibonacci) System.out.print(i + ", ");
    }
}
