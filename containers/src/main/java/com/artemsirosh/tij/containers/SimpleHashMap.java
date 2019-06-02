package com.artemsirosh.tij.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 18.03.17.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleHashMap.class);
    private static final int DEF_SIZE = 997;
    private static final float DEF_LOAD_FACTOR = 0.75f;
    private final float LOAD_FACTOR;

    private MapEntry<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        buckets = new MapEntry[DEF_SIZE];
        LOAD_FACTOR = DEF_LOAD_FACTOR;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        boolean found = false;
        MapEntry<K, V> bucket;
        int index = getIndex(key.hashCode());

        if (buckets[index] == null)
            buckets[index] = new MapEntry<>();

        bucket = buckets[index];

        int probes = 0;
        while (bucket.hasNext()) {
            if (bucket.getKey().equals(key)) {
                oldValue = bucket.getValue();
                bucket.setValue(value);
                found = true;
                break;
            }
            bucket = bucket.getNext();
        }


        if (!found) {
            buckets[index] = new MapEntry<>(key, value, buckets[index]);
        }

        rehash();

        return oldValue;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex(key.hashCode());
        MapEntry<K, V> bucket = buckets[index];
        if (bucket != null) {
            while (bucket.hasNext()) {
                if (bucket.getKey().equals(key))
                    return true;

                bucket = bucket.getNext();
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = getIndex(key.hashCode());
        MapEntry<K, V> bucket = buckets[index];
        if (bucket != null) {
            while (bucket.hasNext()) {
                if (bucket.getKey().equals(key))
                    return bucket.getValue();
                bucket = bucket.getNext();
            }
        }

        return null;
    }


    @Override
    public V remove(Object key) {
        V oldValue = null;
        int index = getIndex(key.hashCode());
        MapEntry<K, V> bucket = buckets[index];
        MapEntry<K, V> oldPair = null;

        if (bucket != null) {
            while (bucket.hasNext()) {
                if (bucket.getKey().equals(key)) {
                    oldValue = bucket.getValue();
                    if (oldPair != null) {
                        oldPair.setNext(bucket.getNext());
                    } else {
                        buckets[index] = bucket.getNext();
                    }
                    break;
                }

                oldPair = bucket;
                bucket = bucket.getNext();
            }
        }

        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < DEF_SIZE; i++) {
            if (buckets[i] != null)
                buckets[i] = new MapEntry<>();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (MapEntry<K, V> bucket : buckets) {
            if (bucket == null)
                continue;

            while (bucket.hasNext()) {
                entries.add(bucket);
                bucket = bucket.getNext();
            }
        }

        return entries;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {

        int count = 0;
        for (MapEntry<K, V> bucket : buckets)
            if (bucket != null)
                count++;

        if (count / buckets.length > LOAD_FACTOR) {

            MapEntry<K, V>[] result = new MapEntry[buckets.length + buckets.length / 2];
            System.arraycopy(buckets, 0, result, 0, buckets.length);
            buckets = result;
        }
    }

    private int getIndex(int hash) {
        return Math.abs(hash) % buckets.length;
    }
}
