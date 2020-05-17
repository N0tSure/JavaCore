package com.artemsirosh.tij.concurrency;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

/**
 * Created on 16 May, 2020.
 *
 * Very heavy object.
 *
 * @author Artemis A. Sirosh
 */
public class Heavy {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final int id;
    private double number;

    public Heavy() {
        this.id = counter.getAndIncrement();
        this.number = DoubleStream.generate(Math::random)
                .limit(1_000_000)
                .reduce(0, (prev, cur) -> prev + cur / Math.PI);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Heavy heavy = (Heavy) o;
        return id == heavy.id &&
                Double.compare(heavy.number, number) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Heavy.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("number=" + number)
                .toString();
    }
}
