package com.artemsirosh.tij.concurrency.pairs;

/**
 * Created at 10-06-2019
 *
 * Will be thrown if {@link Pair} values not equal.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class PairValueNotEqualException extends IllegalStateException {

    PairValueNotEqualException(Pair pair) {
        super("Pair values not equal: " + pair);
    }
}
