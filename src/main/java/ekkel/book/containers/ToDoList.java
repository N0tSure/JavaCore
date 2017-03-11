package ekkel.book.containers;

import java.util.PriorityQueue;

/**
 * Created by cresh on 12.03.17.
 */
class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {

    public boolean add( String todo, char primary, int secondary) {
        return super.add(new ToDoItem(primary, secondary, todo));
    }

    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;
        private int secondary;
        private String item;

        public ToDoItem(char primary, int secondary, String item) {
            this.primary = primary;
            this.secondary = secondary;
            this.item = item;
        }

        @Override
        public int compareTo(ToDoItem o) {
            if (primary > o.primary)
                return 1;

            if (primary == o.primary)
                if (secondary > o.secondary)
                    return 1;
                else if (secondary == o.secondary)
                    return 0;

            return -1;
        }

        @Override
        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }
    }
}
