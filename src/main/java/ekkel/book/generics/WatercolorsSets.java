package ekkel.book.generics;

import ekkel.book.util.Sets;

import java.util.EnumSet;
import java.util.Set;

import static ekkel.book.generics.Watercolors.*;
/**
 * Created by cresh on 28.08.16.
 */
class WatercolorsSets {
    public static void main(String[] args) {
        Set<Watercolors> first = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> second = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);

        System.out.println("First: " + first);
        System.out.println("Second: " + second);
        System.out.println("Sets.union(first,second): " + Sets.union(first,second));
        Set<Watercolors> subset = Sets.intersection(first,second);
        System.out.println("Sets.intersection(first,second): " + subset);
        System.out.println("Sets.difference(first,subset): " + Sets.difference(first,subset));
        System.out.println("Sets.difference(second,subset): " + Sets.difference(second,subset));
        System.out.println("Sets.complement(first,second): " + Sets.complement(first,second));

    }
}
