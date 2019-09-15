package com.artemsirosh.tij.finisher;

import java.util.Optional;

/**
 * Created at 16-09-2019
 *
 * This component holds value. Thread safe.
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
class ReturnValueHolder<T> {

    private T value;

    synchronized void setValue(T value) {
        this.value = value;
    }

    synchronized Optional<T> getValue() {
        return Optional.ofNullable(value);
    }
}
