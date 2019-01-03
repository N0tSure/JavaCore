package ekkel.book.containers;

import ekkel.book.util.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;


/**
 * Created by cresh on 12.03.17.
 */
public class QueueBehavior {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueBehavior.class);

    private Generator<String> generator = new Generator<String>() {
        String[] s = "one two three four five six seven eight".split(" ");
        int i = 0;
        @Override
        public String next() {
            return s[i++ % s.length];
        }
    };

    @org.junit.Test
    public void integerHolderTest() throws Exception {
        Queue<IntegerHolder> queue = new PriorityQueue<>();
        for (int i = 0; i < 15; i++) {
            queue.offer(new IntegerHolder());
        }

        StringBuilder builder = new StringBuilder();
        while (queue.peek() != null)
            builder.append(queue.poll()).append(" \n");
        LOGGER.info(builder.toString());
    }

    @org.junit.Test
    public void prioryQueueTest() throws Exception {
        ToDoList toDoList = new ToDoList();
        toDoList.add("Empty trash", 'C', 4);
        toDoList.add("Feed dog", 'A', 2);
        toDoList.add("Feed bird", 'B', 7);
        toDoList.add("Mow lawn", 'C', 3);
        toDoList.add("Water lawn", 'A', 1);
        toDoList.add("Feed cat", 'B', 1);
        while(!toDoList.isEmpty())
            LOGGER.info(toDoList.remove().toString());

    }

    @org.junit.Test
    public void test() {
        pushAndPop(new LinkedList<>(), generator);
        pushAndPop(new PriorityQueue<>(), generator);
        pushAndPop(new ArrayBlockingQueue<>(10), generator);
        pushAndPop(new ConcurrentLinkedQueue<>(), generator);
        pushAndPop(new LinkedBlockingQueue<>(), generator);
        pushAndPop(new PriorityBlockingQueue<>(), generator);
    }

    private <T> void pushAndPop(Queue<T> queue, Generator<T> generator) {
        for (int i = 0; i < 10; i++) {
            queue.offer(generator.next());
        }

        StringBuilder builder = new StringBuilder("[ ");
        while (queue.peek() != null)
            builder.append(queue.remove()).append(", ");
        builder.delete(builder.length() - 2, builder.length());
        builder.append(" ]");
        LOGGER.info("{}: {}", queue.getClass().getSimpleName(), builder);
    }
}
