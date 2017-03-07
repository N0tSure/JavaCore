package ekkel.book.containers;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by cresh on 06.03.17.
 */
public class SequentialList<T> {
    private int size;
    private Node<T> sentinel;

    public SequentialList(int size) {
        this.size = size;
        this.sentinel = new Node<T>();
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
            private int index = 0;
            @Override
            public boolean hasNext() {
                return sentinel.end();
            }

            @Override
            public T next() {
                T result = sentinel.getT();
                if (!sentinel.end()) sentinel = sentinel.getNext();
                return result;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public T previous() {
                return null;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {

            }

            @Override
            public void add(T t) {

            }
        };
    }
}
