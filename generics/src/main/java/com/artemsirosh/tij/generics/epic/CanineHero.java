package com.artemsirosh.tij.generics.epic;

/**
 * Created by cresh on 03.12.16.
 */
class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {
    public CanineHero(POWER power) {
        super(power);
    }
    void hear() {
        this.getPower().hearSubtleNoises();
    }

    void smell() {
        this.getPower().trackBySmell();
    }
}
