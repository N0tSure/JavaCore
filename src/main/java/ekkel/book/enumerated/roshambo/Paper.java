package ekkel.book.enumerated.roshambo;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
class Paper implements Item {

    @Override
    public Outcome compete(Item it) {
        return it.eval(this);
    }

    @Override
    public Outcome eval(Paper p) {
        return Outcome.DRAW;
    }

    @Override
    public Outcome eval(Scissors s) {
        return Outcome.WIN;
    }

    @Override
    public Outcome eval(Rock r) {
        return Outcome.LOSE;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
