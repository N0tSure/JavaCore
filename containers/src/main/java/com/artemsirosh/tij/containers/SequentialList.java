package com.artemsirosh.tij.containers;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by cresh on 06.03.17.
 */
public class SequentialList<T> {
    private Node<T> last;


    @SuppressWarnings("unchecked")
    public SequentialList(Collection<T> c) {
        this.last = new Node<>();

        Object[] o = c.toArray();
        for (int i = o.length - 1; i >= 0; i--) {
            this.last = new Node<>((T) o[i], this.last);
        }
    }

    private static class Node<T> {
        private T t;
        private Node<T> next;

        public Node(T t, Node<T> next) {
            this.t = t;
            this.next = next;
        }

        private Node() {
            this.t = null;
            this.next = null;
        }

        public T getT() {
            return t;
        }

        public Node<T> getNext() {
            return next;
        }



        public boolean end() {
            return t == null && next == null;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof Node)) return false;
            Node node = (Node) obj;
            if (node.t == null) return false;
            return t.equals(node.getT());
        }
    }

    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private Node<T> previous = null;
            private Node<T> lastPassed = null;
            private Node<T> current = last;
            private int index = -1;
            @Override
            public boolean hasNext() {
                return !current.end();
            }

            @Override
            public T next() {
                T result = current.getT();
                previous = lastPassed;
                lastPassed = current;
                current = current.getNext();
                return result;
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                return index+1;
            }

            @Override
            public int previousIndex() {
                return index;
            }

            @Override
            public void remove() {
                if (previous == null && lastPassed == null)
                    throw new IllegalStateException();
                if (lastPassed == last)
                    last = current;
                else
                    previous.next = current;
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                if (previous == null && lastPassed == null)
                    throw new IllegalStateException();
                lastPassed.next = new Node<>(t, current);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[ ");
        Iterator<T> iterator = this.listIterator();
        while (iterator.hasNext())
            builder.append(iterator.next()).append(", ");

        builder.delete(builder.length() - 2, builder.length());
        return builder.append(" ]").toString();
    }
}
