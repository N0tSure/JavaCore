package ekkel.book.enumerated.roshambo;

import java.util.EnumMap;

import static ekkel.book.enumerated.roshambo.Outcome.*;

/**
 * Created on 09 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum RoShamBo5 implements Competitor<RoShamBo5> {

    PAPER, SCISSORS, ROCK;

    private static final EnumMap<RoShamBo5, EnumMap<RoShamBo5, Outcome>> TABLE;

    static {
        TABLE = new EnumMap<>(RoShamBo5.class);
        for (RoShamBo5 it : RoShamBo5.class.getEnumConstants()) {
            TABLE.put(it, new EnumMap<>(RoShamBo5.class));
        }

        initRow(PAPER, DRAW, WIN, LOSE);
        initRow(ROCK, LOSE, DRAW, WIN);
        initRow(SCISSORS, WIN, LOSE, DRAW);
    }

    private static void initRow(RoShamBo5 it, Outcome paper, Outcome rock, Outcome scissors) {
        final EnumMap<RoShamBo5, Outcome> row = RoShamBo5.TABLE.get(it);
        row.put(PAPER, paper);
        row.put(ROCK, rock);
        row.put(SCISSORS, scissors);
    }

    @Override
    public Outcome compete(RoShamBo5 it) {
        return TABLE.get(this).get(it);
    }
}
