package com.artemsirosh.tij.generics.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by cresh on 20.12.16.
 */
class Functional {
    static <T> T reduce(Iterable<T> sequence, Combiner<T> combiner) {
        Iterator<T> iterator = sequence.iterator();
        if (iterator.hasNext()) {
            T result = iterator.next();
            while (iterator.hasNext()) {
                result = combiner.combine(result, iterator.next());
            }
            return result;
        }
        return null;
    }

    static <T> Collector<T> forEach(Iterable<T> sequence, Collector<T> function) {
        for (T t: sequence) {
            function.function(t);
        }
        return function;
    }

    static <R, T> List<R> transform(Iterable<T> sequence, UnaryFunction<R, T> function) {
        List<R> result = new ArrayList<R>();
        sequence.forEach((t) -> result.add(function.function(t)));
        return result;
    }

    static <T> List<T> filter(Iterable<T> sequence, UnaryPredicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T t: sequence) {
            if (predicate.test(t))
                result.add(t);
        }
        return result;
    }
}
