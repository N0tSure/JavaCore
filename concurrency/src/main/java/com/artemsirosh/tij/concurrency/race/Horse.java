package com.artemsirosh.tij.concurrency.race;

import java.util.stream.IntStream;

/**
 * Created at 09-07-2019
 *
 * Represent a horse.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public interface Horse extends Runnable {

    /**
     * Returns identifier.
     * @return id
     */
    int getId();

    /**
     * Returns strides for current horse.
     * @return strides
     */
    int getStrides();

    /**
     * Returns representation of track distance that pass through this horse.
     * @return track distance
     */
    default String tracks() {
        return IntStream.range(0, getStrides())
                .mapToObj(i -> "*")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .append(getId())
                .toString();
    }
}
