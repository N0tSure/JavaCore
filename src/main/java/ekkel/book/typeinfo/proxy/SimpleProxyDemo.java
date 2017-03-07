package ekkel.book.typeinfo.proxy;

import java.lang.reflect.Proxy;
import java.util.Calendar;

/**
 * Created by cresh on 15.08.16.
 */
class SimpleProxyDemo {
    private static long timer;

    private static void mark() {
        timer = System.nanoTime();
    }

    private static long getExecutionTime() {
        return System.nanoTime() - timer;
    }

    static void consumer(Inteface inteface) {
        inteface.doSomething();
        inteface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        mark();
        consumer(real);
        long forReal = getExecutionTime();
        mark();
        consumer(new SimpleProxy(new RealObject()));
        long forSimple = getExecutionTime();
        Inteface proxy = (Inteface) Proxy.newProxyInstance(Inteface.class.getClassLoader(),
                new Class[]{Inteface.class},new DynamicProxyHandler(real));
        mark();
        consumer(proxy);
        long forDynamic = getExecutionTime();
        System.out.println();
        System.out.println("Обычный объект: " + forReal);
        System.out.println("Обычная заглушка: " + forSimple);
        System.out.println("Динамичексая заглушка: " + forDynamic);
    }
}
