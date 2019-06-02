package com.artemsirosh.tij.generics.wildcards;

import java.util.List;

/**
 * Created by cresh on 12.12.16.
 */
public class Holder<T> {
    private T t;
    Holder() {}
    protected Holder(T t) {
        this.t = t;
    }

    protected T get() {
        return t;
    }

    void set(T t) {
        this.t = t;
    }

    private static void primitives() {
        Holder<Boolean> booleanHolder = new Holder<>(false);
        Holder<Byte> byteHolder = new Holder<>((byte) 127);
        Holder<Character> characterHolder = new Holder<>('b');
        Holder<Short> shortHolder = new Holder<>((short) 12589);
        Holder<Integer> integerHolder = new Holder<>(394850);
        Holder<Long> longHolder = new Holder<>(394850892L);
        Holder<Float> floatHolder = new Holder<>(3.1415f);
        Holder<Double> doubleHolder = new Holder<>(3.141500000);

        byte bt = 58;
        char ch = 'c';
        short sh = 3457;
        int d = 4566778;
        boolean bl = true;
        long ld = 345678890;
        float f = 1.333333333333333333f;
        double bf = 2.33333333333333333333333;

        byteHolder.set(bt);
        characterHolder.set(ch);
        shortHolder.set(sh);
        integerHolder.set(d);
        booleanHolder.set(bl);
        longHolder.set(ld);
        floatHolder.set(f);
        doubleHolder.set(bf);

        System.out.printf("%2X ",byteHolder.get());
        System.out.printf("%c ",characterHolder.get());
        System.out.printf("%o ",shortHolder.get());
        System.out.printf("%d ",integerHolder.get());
        System.out.printf("%b ",booleanHolder.get());
        System.out.printf("%d ",longHolder.get());
        System.out.printf("%.4e ",floatHolder.get());
        System.out.printf("%.8a\n",doubleHolder.get());
    }

    @Override
    public boolean equals(Object obj) {
        return t.equals(obj);
    }

    private static void writeTo(List<? super Apple> apples) {
        apples.add(new Apple());
        apples.add(new Jonathan());
//        apples.add(new Fruit());
    }

    public static void main(String[] args) {
        Holder<Apple> apple = new Holder<>(new Apple());
        Apple tmp = apple.get();
        Holder<? extends Fruit> fruit = apple;
        Fruit tmp2 = fruit.get();
        tmp = (Apple) fruit.get();
        try {
            Orange orange = (Orange) fruit.get();
        } catch (Exception exc) {
            System.err.println(exc);
        }
//        fruit.set(new Apple());
//        fruit.set(new Fruit());
        System.out.println(fruit.equals(tmp));

        Holder.primitives();
    }
}
