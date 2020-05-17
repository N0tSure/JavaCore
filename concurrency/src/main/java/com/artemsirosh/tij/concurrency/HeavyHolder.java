package com.artemsirosh.tij.concurrency;

/**
 * Created on 16 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public interface HeavyHolder {

    /**
     * Creates and then returns the same instance of {@link Heavy}.
     * @return {@link Heavy} instance.
     */
    Heavy getHeavy();
}
