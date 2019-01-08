package ekkel.book.enumerated.roshambo;

import static ekkel.book.enumerated.roshambo.Outcome.*;

/**
 * Created on 09 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum RoShamBo3 implements Competitor<RoShamBo3> {

    PAPER {

        @Override
        public Outcome compete(RoShamBo3 it) {
            switch(it) {
                default: // To placate the compiler
                case PAPER:
                    return DRAW;
                case SCISSORS:
                    return LOSE;
                case ROCK:
                    return WIN;
            }
        }
    },

    SCISSORS {

        @Override
        public Outcome compete(RoShamBo3 it) {
            switch(it) {
                default:
                case PAPER:
                    return WIN;
                case SCISSORS:
                    return DRAW;
                case ROCK:
                    return LOSE;
            }
        }
    },

    ROCK {

        @Override
        public Outcome compete(RoShamBo3 it) {
            switch(it) {
                default:
                case PAPER:
                    return LOSE;
                case SCISSORS:
                    return WIN;
                case ROCK:
                    return DRAW;
            }
        }
    };

    public abstract Outcome compete(RoShamBo3 it);
}
