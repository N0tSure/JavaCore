package ekkel.book.enumerated.roshambo;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
