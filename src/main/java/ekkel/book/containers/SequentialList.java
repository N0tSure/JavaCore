package ekkel.book.containers;

import java.util.ListIterator;

/**
 * Created by cresh on 06.03.17.
 */
public class SequentialList<T> {
    private Node<T> sentinel;

    public SequentialList() {
        this.sentinel = new Node<>();
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
            private Node<T> current = sentinel;
            private int index = -1;
            @Override
            public boolean hasNext() {
                return !current.end();
            }

            @Override
            public T next() {
                T result = current.getT();
                previous = current;
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
                Node<T> next = current.getNext();
                previous.next = next;
                current = next;
            }

            @Override
            public void set(T t) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(T t) {
                current = new Node<>(t, current);
                if (previous != null) {
                    previous.next = current;
                }
            }
        };
    }
}
