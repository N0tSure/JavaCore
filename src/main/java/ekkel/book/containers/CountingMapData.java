package ekkel.book.containers;

import java.util.*;

/**
 * Created by cresh on 05.03.17.
 */
public class CountingMapData extends AbstractMap<Integer, String> {
    private int size;
    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
            .split(" ");

    public CountingMapData(int size) {
        this.size = size;
    }

    private static class Entry implements Map.Entry<Integer, String> {
        private int index;
        private Entry(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object obj) {
            return Integer.valueOf(index).equals(obj);
        }

        @Override
        public Integer getKey() {
            return index;
        }

        @Override
        public String getValue() {
            return chars[index % chars.length] + Integer.toString(index / chars.length);
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        return new EntrySet(size);
    }

    private static class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
        private int size;

        private EntrySet(int size) {
            this.size = size < 0 ? 0 : size;
        }

        @Override
        public Iterator<Map.Entry<Integer, String>> iterator() {
            return new Iterator<Map.Entry<Integer, String>>() {
                private Entry entry = new Entry(-1);
                @Override
                public boolean hasNext() {
                    return entry.index < size - 1;
                }

                @Override
                public Map.Entry<Integer, String> next() {
                    entry.index++;
                    return entry;
                }
            };
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
