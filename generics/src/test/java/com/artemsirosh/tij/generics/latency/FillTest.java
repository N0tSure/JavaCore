package com.artemsirosh.tij.generics.latency;

import com.artemsirosh.tij.generics.coffee.Cappuccino;
import com.artemsirosh.tij.generics.coffee.Coffee;
import com.artemsirosh.tij.generics.coffee.Latte;
import com.artemsirosh.tij.generics.coffee.Mocha;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cresh on 20.12.16.
 */
public class FillTest {
    @Test
    public void test() {
        List<Coffee> carrier = new ArrayList<>();
        Fill.fill(new AddableCollectionAdapter<Coffee>(carrier), Coffee.class, 3);
        Fill.fill(Adapter.collectionAdapter(carrier), Latte.class, 2);
        for (Coffee coffee : carrier) {
            System.out.println(coffee);
        }
        System.out.println();
        AddableSimpleQueue<Coffee> queue = new AddableSimpleQueue<>();
        Fill.fill(queue, Mocha.class, 4);
        Fill.fill(queue, Cappuccino.class, 1);
        for (Coffee coffee : queue) {
            System.out.println(coffee);
        }
    }
}
