package ekkel.book.containers;

import ekkel.book.util.FlyweightMap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 05.03.17.
 */
public class ListTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTest.class);

    @Test
    public void seqListTest() throws Exception {
        SequentialList<String> sequentialList = new SequentialList<>(FlyweightMap.names(5));
        ListIterator<String> listIterator = sequentialList.listIterator();

        int c = 0;
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            LOGGER.info("List count# {}, element: {}", c++, s);
            if (s.equals("BURKINA FASO"))
                listIterator.remove();
        }

        LOGGER.info("Removed: {}", sequentialList);
        LOGGER.info("Removed: {}", sequentialList);

        c = 0;
        listIterator = sequentialList.listIterator();
        while (listIterator.hasNext()) {
            LOGGER.info("List count# {}, element: {}", ++c, listIterator.next());
            if (c == 2)
                listIterator.add("one");
        }

        LOGGER.info("Added: {}", sequentialList);

    }

    @Test
    public void iterationTest() {
        List<String> arrayList = new ArrayList<>(FlyweightMap.names(15));
        List<String> linkedList = new LinkedList<>(FlyweightMap.names(15));

        print(arrayList.iterator(), "ArrayList");
        print(linkedList.iterator(), "LinkedList");

        ListIterator<String> arrayIterator = arrayList.listIterator();
        ListIterator<String> linkedIterator = linkedList.listIterator();

        while (arrayIterator.hasNext() && linkedIterator.hasNext()) {
            linkedIterator.next();
            if (linkedIterator.hasNext())
                linkedIterator.next();
            linkedIterator.add(arrayIterator.next());
        }
        print(linkedList.iterator(), "Patched forward");

        arrayIterator = arrayList.listIterator(arrayList.size());
        linkedIterator = linkedList.listIterator(linkedList.size());

        while (linkedIterator.hasPrevious() && arrayIterator.hasPrevious()) {
            linkedIterator.previous();
            if (linkedIterator.hasPrevious())
                linkedIterator.previous();
            linkedIterator.add(arrayIterator.previous());
        }
        print(linkedList.iterator(), "Patched backward");
    }

    private void print(Iterator<String> iterator, String msg) {
        String traverses = "";
        while (iterator.hasNext())
            traverses += iterator.next() + " ";
        LOGGER.info("{}: {}", msg, traverses);
    }
}
