package ekkel.book.generics.epic;

/**
 * Created by cresh on 03.12.16.
 */
class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {
    SuperSleuth(POWER power) {
        super(power);
    }
    void see() {
        getPower().seeThroughWalls();
    }
}
