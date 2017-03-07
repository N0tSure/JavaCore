package ekkel.book.generics.latency;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cresh on 19.12.16.
 */
class Communicate {
    static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            } catch (NoSuchMethodException exc) {
                System.out.println(spkr.getSimpleName() + " cannot speak");
            }
            try {
                Method sit = spkr.getMethod("sit");
                sit.invoke(speaker);
            } catch (NoSuchMethodException exc) {
                System.out.println(spkr.getSimpleName() + " cannot sit");
            }
        } catch (Exception exc) {
            throw new RuntimeException(speaker.toString(), exc);
        }
    }
}
