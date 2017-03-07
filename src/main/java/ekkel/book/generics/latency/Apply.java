package ekkel.book.generics.latency;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cresh on 19.12.16.
 */
public class Apply {
    public static <T, S extends Iterable<T>>
    void apply(S sequence, Method method, Object... args) {
        try {
            for (T t : sequence)
                method.invoke(t, args);
        } catch (IllegalAccessException | InvocationTargetException exc) {
            throw new RuntimeException(exc);
        }
    }
}
