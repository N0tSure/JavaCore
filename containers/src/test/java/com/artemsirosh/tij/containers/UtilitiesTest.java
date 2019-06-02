package com.artemsirosh.tij.containers;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.artemsirosh.tij.util.FlyweightMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 01.04.17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilitiesTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(UtilitiesTest.class);

    private static List<String> list = Arrays.asList("one Two three Four five six one".split(" "));
    private static Collection<String> data = (FlyweightMap.names(6));


    @org.junit.Test(expected = ConcurrentModificationException.class)
    public void failFastTest() throws Exception {
        Collection<String> collection = new ArrayList<>();
        Iterator<String> iterator = collection.iterator();
        collection.add("foo");
        iterator.next();
    }


    @org.junit.Test
    public void unmodifiableTest() throws Exception {
        Collection<String> collection = Collections.unmodifiableCollection(new ArrayList<>(data));
        LOGGER.info("unmodifiableCollection: {}", collection); // Reading is OK
//         collection.add("one"); // Can't change it

        List<String> unmodifiableList = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> lit = unmodifiableList.listIterator();
        LOGGER.info(lit.next()); // Reading is OK
//        lit.add("one"); // Can't change it

        Set<String> unmodifiableSet = Collections.unmodifiableSet(new HashSet<>(data));
        LOGGER.info("unmodifiableSet: {}", unmodifiableSet); // Reading is OK
//        unmodifiableSet.add("one"); // Can't change it

        // For a SortedSet:
        Set<String> unmodifiableSortedSet = Collections.unmodifiableSortedSet(new TreeSet<>(data));
        LOGGER.info("unmodifiableSortedSet: {}", unmodifiableSortedSet);

        Map<String,String> unmodifiableMap = Collections.unmodifiableMap(new HashMap<>(FlyweightMap.capitals(6)));
        LOGGER.info("unmodifiableMap: {}", unmodifiableMap); // Reading is OK
//        unmodifiableMap.put("Ralph", "Howdy!");

        // For a SortedMap:
        Map<String,String> unmodifiableSortedMap =
                Collections.unmodifiableSortedMap(new TreeMap<>(FlyweightMap.capitals(6)));

        LOGGER.info("unmodifiableSortedMap: {}", unmodifiableSortedMap);

    }

    @org.junit.Test
    public void immutableTest() throws Exception {
        List<String> list = new ImmutableList.Builder<String>().addAll(data).build();
        LOGGER.info("Immutable list: {}", list);
        mutableChecker(list);

        Set<String> immutableSet = new ImmutableSet.Builder<String>().addAll(data).build();
        mutableChecker(immutableSet);

        Map<String, String> immutableMap =
                new ImmutableMap.Builder<String, String>().putAll(FlyweightMap.capitals(6)).build();

        mutableChecker(immutableMap);

    }

    @org.junit.Test
    public void sortingTest() throws Exception {
        List<String> list = new ArrayList<>(UtilitiesTest.list);
        list.addAll(UtilitiesTest.list);
        LOGGER.info("Original: {}", list);
        Collections.shuffle(list, new Random(47));
        LOGGER.info("Shuffled: " + list);
        // Use a ListIterator to trim off the last elements:
        ListIterator<String> it = list.listIterator(10);
        while(it.hasNext()) {
            it.next();
            it.remove();
        }
        LOGGER.info("Trimmed: " + list);
        Collections.sort(list);
        LOGGER.info("Sorted: " + list);
        String key = list.get(7);
        int index = Collections.binarySearch(list, key);
        LOGGER.info("Location of {} is {}, list.get({}) = {}", key, index, index, list.get(index));
        list.sort(String::compareToIgnoreCase);
        LOGGER.info("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key, String.CASE_INSENSITIVE_ORDER);
        LOGGER.info("Location of {} is {}, list.get({}) = {}", key, index, index, list.get(index));
    }

    @org.junit.Test
    public void collectionTest() {
        // original
        LOGGER.info("Original: {}", list);
        // disjoint
        LOGGER.info("'list' disjoint (Four)?: " + Collections.disjoint(list, Collections.singletonList("Four")));
        // max
        LOGGER.info("max: " + Collections.max(list));
        // min
        LOGGER.info("min: " + Collections.min(list));
        // max CASE_INSENSITIVE_ORDER
        LOGGER.info("max w/ comparator: " + Collections.max(list, String.CASE_INSENSITIVE_ORDER));
        // min CASE_INSENSITIVE_ORDER
        LOGGER.info("min w/ comparator: " + Collections.min(list, String.CASE_INSENSITIVE_ORDER));
        // sublist
        List<String> sublist = Arrays.asList("Four five six".split(" "));
        LOGGER.info("sublist: {}", sublist);
        // indexOfSubList
        LOGGER.info("indexOfSubList: " + Collections.indexOfSubList(list, sublist));
        // lastIndexOfSubList
        LOGGER.info("lastIndexOfSubList: " + Collections.lastIndexOfSubList(list, sublist));
        // replaceAll
        Collections.replaceAll(list, "one", "Yo");
        LOGGER.info("replaceAll: " + list);
        // reverse
        Collections.reverse(list);
        LOGGER.info("reverse: " + list);
        // rotate
        Collections.rotate(list, 3);
        LOGGER.info("rotate: " + list);
        // copy of
        List<String> source = Arrays.asList("in the matrix".split(" "));
        Collections.copy(list, source);
        LOGGER.info("copy: " + list);
        // swap
        Collections.swap(list, 0, list.size() - 1);
        LOGGER.info("swap: " + list);
        // shuffle
        Collections.shuffle(list, new Random(47));
        LOGGER.info("shuffled: " + list);
        // fill
        Collections.fill(list, "pop");
        LOGGER.info("fill: " + list);
        // frequency
        LOGGER.info("frequency of 'pop': " + Collections.frequency(list, "pop"));

        List<String> dups = Collections.nCopies(3, "snap");
        LOGGER.info("dups: " + dups);
        LOGGER.info("'list' disjoint 'dups'?: " + Collections.disjoint(list, dups));
        // Getting an old-style Enumeration:
        Enumeration<String> e = Collections.enumeration(dups);
        Vector<String> v = new Vector<>();
        while(e.hasMoreElements())
            v.addElement(e.nextElement());
        // Converting an old-style Vector
        // to a List via an Enumeration:
        ArrayList<String> arrayList =
                Collections.list(v.elements());
        LOGGER.info("arrayList: " + arrayList);
    }

    private void mutableChecker(Set<String> set) {
        try {
            LOGGER.info("Set.contains(\"foo\"): {}", set.contains("foo"));
        } catch (Exception exc) {
            LOGGER.error("Set.contains(): ", exc);
        }

        try {
            LOGGER.info("set.add(\"foo\"): {}", set.add("foo"));
        } catch (Exception exc) {
            LOGGER.error("Set.add(): ", exc);
        }

        try {
            LOGGER.info("set.remove(\"foo\"): {}", set.remove("foo"));
        } catch (Exception exc) {
            LOGGER.error("Set.remove(): ", exc);
        }
    }

    private void mutableChecker(List<String> list) {
        try {
            LOGGER.info("list.add(\"foo\"): {}", list.add("foo"));
        } catch (Exception exc) {
            LOGGER.error("list.add(\"foo\"): ", exc);
        }

        try {
            LOGGER.info("list.remove(\"foo\"): {}", list.remove("foo"));
        } catch (Exception exc) {
            LOGGER.error("list.remove(\"foo\"): ", exc);
        }

    }

    private void mutableChecker(Map<String, String> map) {
        try {
            LOGGER.info("Map.containsKey(\"foo\"): {}", map.containsKey("foo"));
        } catch (Exception exc) {
            LOGGER.error("Map.containsKey(): ", exc);
        }

        try {
            LOGGER.info("map.put(\"foo\", \"bar\"): {}", map.put("foo", "bar"));
        } catch (Exception exc) {
            LOGGER.error("map.put(\"foo\", \"bar\"): ", exc);
        }

        try {
            LOGGER.info("map.remove(\"foo\"): {}", map.remove("foo"));
        } catch (Exception exc) {
            LOGGER.error("map.remove(\"foo\"): ", exc);
        }

    }

}
