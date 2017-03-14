package ekkel.book.containers;

/**
 * Created by cresh on 14.03.17.
 */
class Prediction {
    private boolean scareShadow;

    public Prediction() {
        this.scareShadow = Math.random() > 0.5;
    }

    @Override
    public String toString() {
        if (scareShadow)
            return "Six more weeks of Winter!";
        else
            return "Early Spring!";
    }
}
