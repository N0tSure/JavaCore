package ekkel.book.containers;

import java.util.Map;

/**
 * Created by cresh on 14.03.17.
 */
class MapEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private MapEntry<K, V> next;

    MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public MapEntry(K key, V value, MapEntry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public MapEntry() {
        key = null;
        value = null;
        next = null;
    }

    boolean hasNext() {
        return (key != null) && (value != null) && (next != null);
    }

    MapEntry<K, V> getNext() {
        return next;
    }

    void setNext(MapEntry<K, V> next) {
        this.next = next;
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
    public int hashCode() {
        return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof MapEntry))
            return false;
        MapEntry mapEntry = (MapEntry) obj;

        return (key == null ? mapEntry.getKey() == null : key.equals(mapEntry.getKey())) &&
                (value == null ? mapEntry.getValue() == null : value.equals(mapEntry.getValue()));
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}
