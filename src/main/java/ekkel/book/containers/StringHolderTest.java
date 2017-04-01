package ekkel.book.containers;

import ekkel.book.util.Generator;
import org.junit.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cresh on 02.04.17.
 */
public class StringHolderTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringHolderTest.class);

    // TODO: 02.04.17 TEST RULE

    @org.junit.Test
    public void sortingTest() {
        List<Holder> holders = Holder.getHolderStream().limit(8000).collect(Collectors.toList());
        LOGGER.info("Before sort: {}", holders);

        Collections.sort(holders);
        LOGGER.info("After first string based sort: {}", holders);

        holders.sort(Holder.getSecondStringComparator());
        LOGGER.info("After second string based sort: {}", holders);
    }

    @Test
    public void collectionElementUsage() throws Exception {
        List<Holder> holders = Holder.getHolderStream().limit(8).collect(Collectors.toList());

        Set<Holder> holderSet = new HashSet<>(holders);
        holderSet.addAll(holders);

        LOGGER.info("Holder Set: {}", holderSet);
        Assert.assertTrue(holderSet.containsAll(holders));
    }
}
