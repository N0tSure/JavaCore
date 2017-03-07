package ekkel.book.typeinfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cresh on 10.08.16.
 */
public class TypeCounter extends HashMap<Class<?>,Integer> {
    private Class<?> base;
    public TypeCounter(Class<?> base) {
        this.base = base;
    }
    public void count(Object o) {
        Class<?> type = o.getClass();
        if (!base.isAssignableFrom(type))
            throw new RuntimeException(
                    String.format("%1$s wrong type: %2$s, must be %3$s",
                            o.toString(),type,base));
        countClass(type);
    }

    private void countClass(Class<?> type) {
        Integer quantity = get(type);
        put(type,quantity == null ? 1 : ++quantity);
        Class<?> superClazz = type.getSuperclass();
        if (superClazz != null && base.isAssignableFrom(superClazz))
            countClass(superClazz);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Map.Entry<Class<?>,Integer> pair : entrySet()) {
            result.append(pair.getKey().getSimpleName());
            result.append("=");
            result.append(pair.getValue());
            result.append(", ");
        }
        result.delete(result.length()-2,result.length());
        result.append("}");
        return new String(result);
    }
}
