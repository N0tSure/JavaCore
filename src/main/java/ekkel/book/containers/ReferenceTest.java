package ekkel.book.containers;

import org.junit.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.WeakHashMap;

/**
 * Created by cresh on 02.04.17.
 */
public class ReferenceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(UtilitiesTest.class);

    private ReferenceQueue<VeryBig> referenceQueue = new ReferenceQueue<>();

    @Test
    public void canonicalMapping() throws Exception {
        int size = 1_000_000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();
        for (int i = 0; i < size; i++) {

            Key key = new Key(String.valueOf(i));
            Value value = new Value(String.valueOf(i));
            if (i % 3 == 0)
                keys[i] = key;

            map.put(key, value);
        }

        System.gc();
    }

    @org.junit.Test
    public void referenceTest() {
        int size = 100;

        LinkedList<SoftReference<VeryBig>> softReferences = new LinkedList<>();
        for (int i = 0; i < size; i++) {

            softReferences.add(new SoftReference<>(new VeryBig("Soft " + i), referenceQueue));
            LOGGER.info("Just created: {}, object {}", softReferences.getLast(), softReferences.getLast().get());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> weakReferences = new LinkedList<>();
        for (int i = 0; i < size; i++) {

            weakReferences.add(new WeakReference<>(new VeryBig("Weak " + i), referenceQueue));
            LOGGER.info("Just created: {}", weakReferences.getLast());
            checkQueue();
        }

        SoftReference<VeryBig> veryBigSoftReference = new SoftReference<>(new VeryBig("Soft"));
        WeakReference<VeryBig> veryBigWeakReference = new WeakReference<>(new VeryBig("Weak"));

        System.gc();

        LinkedList<PhantomReference<VeryBig>> phantomReferences = new LinkedList<>();
        for (int i = 0; i < size; i++) {

            phantomReferences.add(new PhantomReference<>(new VeryBig("Phantom " + i), referenceQueue));
            LOGGER.info("Just created: {}", phantomReferences.getLast());
            checkQueue();
        }
    }


    private void checkQueue() {
        Reference<? extends VeryBig> referenceInQueue = referenceQueue.poll();
        if (referenceInQueue != null)
            LOGGER.info("In ReferenceQueue: {}", referenceInQueue.get());
    }
}
