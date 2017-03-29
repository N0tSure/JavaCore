package ekkel.book.containers;

import java.util.*;

/**
 * Created by cresh on 14.03.17.
 */
public class SlowMap<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private List<MapEntry> innerEntries;

    private class MapEntry implements Map.Entry<K, V>, Comparable<MapEntry> {
        private K key;
        private V value;

        private MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private MapEntry(K key) {
            this.key = key;
            this.value = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public int compareTo(MapEntry o) {
            return this.key.compareTo(o.key);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MapEntry entry = (MapEntry) o;

            return key.equals(entry.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    private  class KeySet extends AbstractSet<K> {

        @Override
        @SuppressWarnings("unchecked")
        public boolean remove(Object o) {
            MapEntry entry = new MapEntry((K) o);
            return innerEntries.remove(entry);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
            MapEntry entry = new MapEntry((K) o);
            return innerEntries.contains(entry);
        }

        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                Iterator<MapEntry> mapEntryIterator = innerEntries.iterator();
                @Override
                public boolean hasNext() {
                    return mapEntryIterator.hasNext();
                }

                @Override
                public K next() {
                    return mapEntryIterator.next().getKey();
                }

                @Override
                public void remove() {
                    mapEntryIterator.remove();
                }
            };
        }

        @Override
        public int size() {
            return innerEntries.size();
        }
    }

    public SlowMap() {
        this.innerEntries = new ArrayList<>();
    }

    @Override
    public V put(K key, V value) {
        V old = null;
        int index = getInsertIndex(key);
        if (index < 0) {
            int insertionPoint = Math.abs(index) - 1;
            innerEntries.add(insertionPoint, new MapEntry(key, value));
        } else {
            old = innerEntries.get(index).getValue();
            innerEntries.get(index).setValue(value);
        }
        return old;
    }

    @Override
    public V get(Object key) {
        int index = getInsertIndex(key);
        if (index < 0)
            return null;

        return innerEntries.get(index).getValue();
    }

    @Override
    public V remove(Object key) {
        int index = getInsertIndex(key);
        V old = null;
        if (index >= 0) {
            old = innerEntries.get(index).getValue();
            innerEntries.remove(index);
        }

        return old;
    }

    @Override
    public void clear() {
        innerEntries.clear();
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(innerEntries);
    }

    @SuppressWarnings("unchecked")
    private int getInsertIndex(Object key) {
        MapEntry keyNode = new MapEntry((K) key);
        return Collections.binarySearch(innerEntries, keyNode);
    }
}
