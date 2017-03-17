package ekkel.book.containers;

import java.util.*;

/**
 * Created by cresh on 14.03.17.
 */
public class SlowMap<K, V> extends AbstractMap<K, V> {
    private List<K> keys;
    private List<V> values;

    private  class KeySet extends AbstractSet<K> {

        @Override
        public boolean remove(Object o) {
            return keys.remove(o);
        }

        @Override
        public boolean contains(Object o) {
            return keys.contains(o);
        }

        @Override
        public Iterator<K> iterator() {
            return keys.iterator();
        }

        @Override
        public int size() {
            return keys.size();
        }
    }

    public SlowMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    @Override
    public V put(K key, V value) {
        V old = this.get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return old;
    }

    @Override
    public V get(Object key) {
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    @Override
    public V remove(Object key) {
        V removed = this.get(key);
        if (keys.contains(key)) {
            int keyIndex = keys.indexOf(key);
            values.remove(keyIndex);
            keys.remove(keyIndex);
        }
        return removed;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        Iterator<K> kIterator = keys.iterator();
        Iterator<V> vIterator = values.iterator();

        while (kIterator.hasNext())
            entrySet.add(new MapEntry<>(kIterator.next(), vIterator.next()));

        return entrySet;
    }
}
