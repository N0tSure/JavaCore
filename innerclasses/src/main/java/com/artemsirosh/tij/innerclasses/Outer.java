package com.artemsirosh.tij.innerclasses;

/**
 * Created by cresh on 29.05.16.
 */
class Outer {
    private int value;

    Outer(int value) {
        this.value = value;
    }

    private class CInner implements Content {
        @Override
        public int value() {
            return value;
        }
    }
    Content content() {
        Content content;
        return new CInner();
    }

    public static void main(String[] args) {
        Outer outer = new Outer(12);
        Content content = outer.content();
        int i = content.value();
        System.out.println(i);
    }
}
