package com.artemsirosh.tij.generics;

/**
 * Created by cresh on 01.12.16.
 */
class SimpleHolder {
    private Object object;

    public Object get() {
        return object;
    }

    public void set(Object object) {
        this.object = object;
    }

    public static void main(String[] args) {
        SimpleHolder holder = new SimpleHolder();
        holder.set("Item");
        String s = (String) holder.get();
    }
}
