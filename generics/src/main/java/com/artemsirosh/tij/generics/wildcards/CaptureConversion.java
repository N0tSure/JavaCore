package com.artemsirosh.tij.generics.wildcards;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cresh on 13.12.16.
 */
class CaptureConversion {
    private static <T> void f1(Holder<T> holder) {
        T t = holder.get();
        System.out.println(t.getClass().getSimpleName());
    }
    private static void f2(Holder<?> holder) {
        f1(holder); // Call with captured type
    }

    private static void f3(Holder<List<?>> holder) {
        holder.set(new LinkedList<Integer>());
        Object integer = holder.get().get(0);
        holder.get().contains(new Object());
        holder.get().equals(new Object());
    }

    private static void f4(List<Holder<?>> list) {
       list.add(new Holder<Integer>());
       list.add(new Holder<Long>());
       list.add(new Holder<>());
       list.add(new Holder());
        Integer integer = (Integer) list.get(0).get();
        Object result = list.get(1).get();
//        list.get(2).set(new Integer(2));
//        list.get(2).set(new Object());
        list.get(2).set(null);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Holder raw = new Holder<Integer>(1);
        // f1(raw); // Produces warnings
        f2(raw); // No warnings
        Holder rawBasic = new Holder();
        rawBasic.set(new Object()); // Warning
        f2(rawBasic); // No warnings
        // Upcast to Holder<?>, still figures it out:
        Holder<?> wildcarded = new Holder<Double>(1.0);
        f2(wildcarded);
    }
}
