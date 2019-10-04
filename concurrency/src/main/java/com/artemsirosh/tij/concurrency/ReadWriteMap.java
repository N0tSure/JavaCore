package com.artemsirosh.tij.concurrency;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created at 04-10-2019
 *
 * @author Artem Sirosh 'ASir2089@gmail.com'
 */
public class ReadWriteMap<K, V> extends AbstractMap<K, V> {

    private final Map<K, V> map;
    private final ReadWriteLock readWriteLock;

    public ReadWriteMap(Map<K, V> map, boolean fair) {
        this.map = map;
        this.readWriteLock = new ReentrantReadWriteLock(fair);
    }

    @Override
    public V get(Object key) {
        final Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        final Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public int size() {
        return map.size();
    }
}
