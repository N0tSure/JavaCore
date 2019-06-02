package com.artemsirosh.tij.util;

import java.util.*;

/**
 * Created by cresh on 04.03.17.
 */
public class FlyweightMap extends AbstractMap<String, String> {
    private static Set<Map.Entry<String, String>> entries = new EntrySet(Countries.DATA.length);
    private static Map<String, String> full = new FlyweightMap();
    private static List<String> names = new ArrayList<>(full.keySet());

    @Override
    public  Set<Map.Entry<String, String>> entrySet() {
        return entries;
    }

    public static List<String> names() {
        return names;
    }

    public static List<String> names(int size) {
        return new ArrayList<>(select(size).keySet());
    }

    public static Map<String, String> select(final int size) {
        return new FlyweightMap() {
            public Set<Map.Entry<String, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }

    public static Map<String, String> capitals() {
        return full;
    }

    public static Map<String, String> capitals(int size) {
        return select(size);
    }

    private static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
        private int size;
        EntrySet(int size) {
            if (size < 0)
                this.size = 0;
            else if (size > Countries.DATA.length)
                this.size = Countries.DATA.length;
            else
                this.size = size;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public Iterator<Map.Entry<String, String>> iterator() {
            return new Iterator<Map.Entry<String, String>>() {
                private Entry entry = new Entry(-1);
                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                @Override
                public Map.Entry<String, String> next() {
                    entry.index++;
                    return entry;
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    private static class Entry implements Map.Entry<String, String> {
        private int index;
        Entry(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            return Countries.DATA[index][0].equals(obj);
        }

        @Override
        public int hashCode() {
            return Countries.DATA[index][0].hashCode();
        }

        @Override
        public String getKey() {
            return Countries.DATA[index][0];
        }

        @Override
        public String getValue() {
            return Countries.DATA[index][1];
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
    }
}
