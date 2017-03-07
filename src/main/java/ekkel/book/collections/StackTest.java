package ekkel.book.collections;

import java.util.LinkedList;

/**
 * Created by NotSure on 03.03.16.
 */
public class StackTest {
    static class Stack<T> {
        private LinkedList<T> storage = new LinkedList<T>();
        public void push(T v) {
            storage.addFirst(v);
        }
        public T peek() {
            return storage.getFirst();
        }
        public T pop() {
            return storage.removeFirst();
        }
        public boolean empty() {
            return storage.isEmpty();
        }

        public String toString() {
            return storage.toString();
        }
    }

    public static void main(String[] args) {
        Stack<String> queue = new Stack<String>();

        for (String s : "Here you are ...".split(" ")) {
            queue.push(s);
        }

        while (!queue.empty()) {
            System.out.print(queue.pop());
        }
    }
}
