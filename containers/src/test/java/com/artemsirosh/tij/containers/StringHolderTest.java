package com.artemsirosh.tij.containers;

import com.artemsirosh.tij.util.Generator;
import org.junit.*;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cresh on 02.04.17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringHolderTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringHolderTest.class);

    @Rule
    public TestWatcher testWatcher = new SimpleTestWatcher();

    @org.junit.Test
    public void sortingTest() {
        List<Holder> holders = Holder.getHolderStream().limit(8000).collect(Collectors.toList());
        LOGGER.info("Before sort: {}", holders);

        Collections.sort(holders);
        LOGGER.info("After first string based sort: {}", holders);

        holders.sort(Holder.getSecondStringComparator());
        LOGGER.info("After second string based sort: {}", holders);
    }

    @org.junit.Test
    public void collectionElementUsage() throws Exception {
        List<Holder> holders = Holder.getHolderStream().limit(8).collect(Collectors.toList());

        Set<Holder> holderSet = new HashSet<>(holders);
        holderSet.addAll(holders);

        LOGGER.info("Holder Set: {}", holderSet);
        Assert.assertTrue(holderSet.containsAll(holders));
    }
}
