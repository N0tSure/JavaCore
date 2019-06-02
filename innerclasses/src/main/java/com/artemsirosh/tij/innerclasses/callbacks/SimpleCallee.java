package com.artemsirosh.tij.innerclasses.callbacks;

/**
 * Created by cresh on 01.07.16.
 */
class SimpleCallee implements Incrementable{
    private int i;
    SimpleCallee() {
        this.i=0;
    }
    @Override
    public void increment() {
        this.i++;
        System.out.println(this.i);
    }


}
