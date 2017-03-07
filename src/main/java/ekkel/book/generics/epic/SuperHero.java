package ekkel.book.generics.epic;

/**
 * Created by cresh on 03.12.16.
 */
class SuperHero<POWER extends SuperPower> {
    private POWER power;
    SuperHero(POWER power) {
        this.power = power;
    }

    POWER getPower() {
        return power;
    }
}
