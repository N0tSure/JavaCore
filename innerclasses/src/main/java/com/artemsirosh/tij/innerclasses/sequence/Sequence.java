package com.artemsirosh.tij.innerclasses.sequence;

import java.util.Iterator;

/**
 * Created by cresh on 29.05.16.
 */
class Sequence<T> {
    private Object[] items;
    private int index;

    Sequence(int size) {
        items = new Object[size];
        index = 0;
    }

    public void add(T t) {
        if (index < items.length ) items[index++] = t;
    }

    public int length() { return items.length; }

    public Selector<T> reverseSelector() {

        return new Selector<T>() {
            private int index = items.length-1;
            @Override
            public boolean end() {
                return index<0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T current() {
                return (T) items[this.index];
            }

            @Override
            public void next() {
                this.index--;
            }
        };
    }

    private class SequenceSelector<T> implements Selector<T>, Iterable<T> {
        private int index = 0;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                //private int index = 0;
                @Override
                public boolean hasNext() {
                    return items.length > index;
                }

                @Override
                @SuppressWarnings("unchecked")
                public T next() {
                    return (T) items[index++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        @Override
        public boolean end() {
            return this.index == items.length;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T current() {
            return (T) items[this.index];
        }

        @Override
        public void next() {
            if (this.index < items.length) this.index++;
        }

        Sequence sequence() { return Sequence.this; }
    }

    public Selector<T> selector() {
        return new SequenceSelector<T>();
    }

    public Iterable<T> iterable() {
        return new SequenceSelector<T>();
    }

    public static void main(String[] args) {
        Sequence<StringBuilder> sequence = new Sequence<>(10);

        for (int i = 0; i < sequence.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            sequence.add(stringBuilder);
        }

        Selector<StringBuilder> selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }

        System.out.println();

        for (Object d : sequence.iterable()) {
            System.out.print(d + " ");
        }

        System.out.println();

        selector = sequence.reverseSelector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
