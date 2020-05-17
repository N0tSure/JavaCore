package com.artemsirosh.tij.concurrency;

import java.util.function.Supplier;

/**
 * Created on 17 May, 2020.
 *
 * @author Artemis A. Sirosh
 */
public class ConcurrentHolder implements HeavyHolder {

    private Supplier<Heavy> heavySupplier = this::createAndCacheHeavy;

    @Override
    public Heavy getHeavy() {
        return heavySupplier.get();
    }

    private synchronized Heavy createAndCacheHeavy() {
        class HeavyFactory implements Supplier<Heavy> {
            private final Heavy heavy = new Heavy();

            @Override
            public Heavy get() {
                return heavy;
            }
        }

        if (!(heavySupplier instanceof HeavyFactory)) {
            heavySupplier = new HeavyFactory();
        }

        return heavySupplier.get();
    }
}
