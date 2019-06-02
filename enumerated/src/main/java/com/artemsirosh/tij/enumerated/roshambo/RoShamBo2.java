package com.artemsirosh.tij.enumerated.roshambo;

import static com.artemsirosh.tij.enumerated.roshambo.Outcome.*;

/**
 * Created on 09 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum RoShamBo2 implements Competitor<RoShamBo2> {

    PAPER(DRAW, WIN, LOSE),
    ROCK(LOSE, DRAW, WIN),
    SCISSORS(WIN, LOSE, DRAW);

    private final Outcome paper, rock, scissors;

    RoShamBo2(Outcome paper, Outcome rock, Outcome scissors) {
        this.paper = paper;
        this.rock = rock;
        this.scissors = scissors;
    }

    @Override
    public Outcome compete(RoShamBo2 it) {
        switch (it) {
            default:
            case PAPER:
                return paper;
            case ROCK:
                return rock;
            case SCISSORS:
                return scissors;
        }
    }
}
