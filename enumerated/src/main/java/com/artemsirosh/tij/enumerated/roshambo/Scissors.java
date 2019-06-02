package com.artemsirosh.tij.enumerated.roshambo;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class Scissors implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.WIN;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
