package com.artemsirosh.tij.containers;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cresh on 02.04.17.
 */
class VeryBig {

    private final static Logger LOGGER = LoggerFactory.getLogger(UtilitiesTest.class);
    private static final int SIZE = 10_000;
    private long[] inner;
    private String id;

    public VeryBig(String id) {
        this.id = id;
        this.inner = new long[SIZE];
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("ID", id).toString();
    }

    @Override
    protected void finalize() throws Throwable {
        LOGGER.info("Finalising: {}", this);
    }
}
