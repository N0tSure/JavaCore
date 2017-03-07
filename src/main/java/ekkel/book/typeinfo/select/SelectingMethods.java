package ekkel.book.typeinfo.select;

import java.lang.reflect.Proxy;

/**
 * Created by cresh on 15.08.16.
 */
class SelectingMethods {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(SomeMethods.class.getClassLoader(),
                new Class[]{SomeMethods.class},new MethodSelector(new Implementation()));
        proxy.aLittleBitBoring();
        proxy.mainlyBoring();
        proxy.interesting("bonobo");
        proxy.veryBoring();
    }
}
