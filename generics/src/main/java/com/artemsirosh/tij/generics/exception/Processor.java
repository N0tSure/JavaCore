package com.artemsirosh.tij.generics.exception;

import java.util.List;

/**
 * Created by cresh on 17.12.16.
 */
interface Processor<T, E extends Exception> {
    void process(List<T> resultCollector) throws E;
}
