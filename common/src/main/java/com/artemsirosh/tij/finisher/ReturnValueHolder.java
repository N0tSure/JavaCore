package com.artemsirosh.tij.finisher;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    synchronized void setValue(@Nullable T value) {
        this.value = value;
    }

    synchronized @NotNull Optional<T> getValue() {
        return Optional.ofNullable(value);
    }
}
