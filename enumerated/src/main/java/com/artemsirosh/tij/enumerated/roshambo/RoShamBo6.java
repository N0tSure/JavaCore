package com.artemsirosh.tij.enumerated.roshambo;

import static com.artemsirosh.tij.enumerated.roshambo.Outcome.*;

/**
 * Created on 09 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {

    PAPER, SCISSORS, ROCK;

    private static final Outcome[][] TABLE = {
            { DRAW, LOSE, WIN }, // PAPER
            { WIN, DRAW, LOSE }, // SCISSORS
            { LOSE, WIN, DRAW }, // ROCK
    };

    @Override
    public Outcome compete(RoShamBo6 it) {
        return TABLE[this.ordinal()][it.ordinal()];
    }
}
