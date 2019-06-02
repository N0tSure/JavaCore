package com.artemsirosh.mfb.loader;

/**
 * Created by cresh on 09.12.16.
 */
public class SModule implements Module {
    @Override
    public void load() {
        System.out.printf("Module: %s: load", this.getClass().getName());
    }

    @Override
    public void run() {
        System.out.printf("Module: %s: running", this.getClass().getName());
    }

    @Override
    public void unload() {
        System.out.printf("Module: %s: unloaded", this.getClass().getName());
    }
}
