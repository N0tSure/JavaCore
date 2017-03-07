package ekkel.book.typeinfo;

import ekkel.book.typeinfo.packageaccess.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cresh on 16.08.16.
 */
public class HiddenImplementation {
    static void callHiddenMethod(Object target, String methodName)  {
        try {
            Method g = target.getClass().getDeclaredMethod(methodName);
            g.setAccessible(true);
            g.invoke(target);
        } catch (IllegalAccessException  | InvocationTargetException | NoSuchMethodException exc) {
            throw new RuntimeException(exc);
        }
    }
    public static void main(String[] args) {
        A a = AnonymousImplementation.makeA();
        a.f();
        System.out.println(a.getClass().getName());

        callHiddenMethod(a,"g");
        callHiddenMethod(a,"u");
        callHiddenMethod(a,"v");
        callHiddenMethod(a,"w");

    }
}
