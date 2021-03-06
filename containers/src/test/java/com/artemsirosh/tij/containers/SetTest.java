package com.artemsirosh.tij.containers;

import com.artemsirosh.tij.util.CollectionData;
import com.artemsirosh.tij.util.FlyweightMap;
import com.artemsirosh.tij.util.RandomGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 11.03.17.
 */
public class SetTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SetTest.class);

    @org.junit.Test
    public void treeSetTest() throws Exception {
        Set<String> sortedSet =
                new TreeSet<>(String::compareToIgnoreCase);
        sortedSet.addAll(new CollectionData<>(RandomGenerator.getStringGenerator(), 10_000));
        LOGGER.info("SortedSet: {}", sortedSet);
    }

    @org.junit.Test
    public void sortedSetTest() throws Exception {
        LOGGER.info("----------------TreeSet----------------");
        sortedSetDemo(new TreeSet<>());
        LOGGER.info("---------------LinkedSet---------------");
        sortedSetDemo(new LinkedSet<>());

    }

    private void sortedSetDemo(SortedSet<String> sortedSet) {
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
        LOGGER.info("Origin: {}", sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        LOGGER.info("low: {}", low);
        LOGGER.info("high: {}", high);
        Iterator<String> it = sortedSet.iterator();
        for(int i = 0; i <= 6; i++) {
            if(i == 3) low = it.next();
            if(i == 6) high = it.next();
            else it.next();
        }
        LOGGER.info("low: {}", low);
        LOGGER.info("high: {}", high);
        LOGGER.info("sortedSet.subSet(low, high): {}", sortedSet.subSet(low, high));
        LOGGER.info("sortedSet.headSet(high): {}", sortedSet.headSet(high));
        LOGGER.info("sortedSet.tailSet(low): {}", sortedSet.tailSet(low));

    }

    @org.junit.Test
    public void setTypeTest() throws Exception {
        test(new HashSet<>(), HashType.class);
        test(new LinkedHashSet<>(), HashType.class);
        test(new TreeSet<>(), TreeType.class);
        test(new SlowSet<>(), HashType.class);
        test(new SimpleHashSet<>(), HashType.class);

        test(new HashSet<>(), SetType.class);
        test(new HashSet<>(), TreeType.class);
        test(new LinkedHashSet<>(), SetType.class);
        test(new LinkedHashSet<>(), TreeType.class);

        try {
            test(new TreeSet<>(), SetType.class);
        } catch (Exception exc) {
            LOGGER.error("setTypeTest: ", exc);
        }

        try {
            test(new TreeSet<>(), HashType.class);
        } catch (Exception exc) {
            LOGGER.error("setTypeTest: ", exc);
        }
    }

    @org.junit.Test
    public void setTest() throws Exception {
        setTest(new SimpleHashSet<>());

    }

    private void setTest(Set<String> set) {
        LOGGER.info(set.getClass().getSimpleName());
        set.addAll(FlyweightMap.names(10));
        set.addAll(FlyweightMap.names(10));
        LOGGER.info(set.toString());
        LOGGER.info("set.containsKey(ALGERIA): " + set.contains("ALGERIA"));
        LOGGER.info("set.remove(\"ALGERIA\"): " + set.remove("ALGERIA"));
        LOGGER.info(set.toString());
        set.clear();
        LOGGER.info("set.isEmpty(): " + set.isEmpty());
    }

    private <T> void test(Set<T> set, Class<T> type) throws Exception {
        fill(set, type);
        fill(set, type);
        fill(set, type);
        LOGGER.info("{} [{}]: {}", set.getClass().getSimpleName(), type.getSimpleName(), set);
    }

    private <T> Set<T> fill(Set<T> set, Class<T> type) throws Exception {
        for (int i = 0; i < 10; i++)
            set.add(type.getConstructor(int.class).newInstance(i));

        return set;

    }
}
