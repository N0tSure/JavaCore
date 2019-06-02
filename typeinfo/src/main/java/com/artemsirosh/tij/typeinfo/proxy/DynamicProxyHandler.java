package com.artemsirosh.tij.typeinfo.proxy;

import com.artemsirosh.tij.typeinfo.pets.Pet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cresh on 15.08.16.
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
        proxy = new Pet();
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method +
        ", args: " + args);
        if (args != null) for (Object argument : args) System.out.println(" " + argument);
        return method.invoke(proxied,args);
    }
}
