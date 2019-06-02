package com.artemsirosh.tij.typeinfo.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cresh on 16.08.16.
 */
class NullPartProxyHandler implements InvocationHandler {
    private Factory<NullPart> proxied = new NullPart();

    @Override
    public Object invoke(Object o, Method method, Object[] arguments) throws Throwable {
        return method.invoke(proxied,arguments);
    }

    private static class NullPart extends Part implements Factory<NullPart> {
        @Override
        public NullPart create() {
            return new NullPart();
        }

        @Override
        public String toString() {
            return "NullPart";
        }
    }
}
