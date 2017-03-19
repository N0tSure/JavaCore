package ekkel.book.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 18.03.17.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleHashMap.class);
    private static final int SIZE = 997;

    private MapEntry<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        buckets = new MapEntry[SIZE];
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        boolean found = false;
        MapEntry<K, V> bucket;
        int index = Math.abs(key.hashCode()) % SIZE;

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

//        LOGGER.info("Amount of probes for {}:{} is {}", key, value, probes);

        if (!found) {
            buckets[index] = new MapEntry<>(key, value, buckets[index]);
        }

        return oldValue;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
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
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntry<K, V> bucket = buckets[index];
        if (bucket != null) {
            while (bucket.hasNext()) {
                if (bucket.getKey().equals(key))
                    return bucket.getValue();
            }
        }

        return null;
    }


    @Override
    public V remove(Object key) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
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
        for (int i = 0; i < SIZE; i++) {
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
}
