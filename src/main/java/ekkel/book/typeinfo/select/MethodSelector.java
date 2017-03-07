package ekkel.book.typeinfo.select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cresh on 15.08.16.
 */
class MethodSelector implements InvocationHandler {
    private Object proxied;

    MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object o, Method method, Object... arguments) throws Throwable {
        if (method.getName().equals("interesting"))
            System.out.println("Обнаружено что-то интересное.");
        return method.invoke(proxied,arguments);
    }
}
