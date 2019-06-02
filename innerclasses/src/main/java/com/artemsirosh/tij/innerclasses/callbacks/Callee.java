package com.artemsirosh.tij.innerclasses.callbacks;

/**
 * Created by cresh on 01.07.16.
 */
class Callee extends MyIncrement {
    private int i;

    Callee() {
        this.i=0;
    }

    @Override
    public void increment() {
        super.increment();
        i++;
        System.out.println(this.i);
    }
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee.this.increment();
        }
    }

    Incrementable getCallBackReference() {
        return new Closure();
    }
}
