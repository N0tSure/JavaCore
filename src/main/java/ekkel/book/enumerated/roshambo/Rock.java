package ekkel.book.enumerated.roshambo;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class Rock implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.LOSE;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.DRAW;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
