package com.artemsirosh.tij.containers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cresh on 11.03.17.
 */
class LinkedSet<T extends Comparable<T>> extends AbstractSet<T> implements SortedSet<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(LinkedSet.class);
    private LinkedList<T> storage;
    private Comparator<T> comparator;

    public LinkedSet() {
        this.storage = new LinkedList<>();
        this.comparator = Comparator.naturalOrder();
        LOGGER.info("Created full set, set natural order");
    }

    public LinkedSet(Comparator<T> comparator) {
        this.comparator = comparator;
        LOGGER.info("Created full set, set {} order", comparator.getClass().getSimpleName());
    }

    public LinkedSet(Collection<T> collection) {
        this.storage = new LinkedList<>(collection);
        this.comparator = Comparator.naturalOrder();
        LOGGER.info("Created set with elements {}, set natural order", collection);
    }

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public boolean add(T t) {
        if (storage.contains(t))
            return false;
        int patchIndex = Math.abs(Collections.binarySearch(storage, t, comparator));
        storage.add(patchIndex - 1, t);
        return true;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        int fromElementIndex = storage.indexOf(fromElement);
        int toElementIndex = storage.indexOf(toElement);
        if (fromElementIndex < 0 || toElementIndex < 0)
            return new LinkedSet<>();
        return new LinkedSet<>(storage.subList(fromElementIndex, toElementIndex));
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        int toElementIndex = storage.indexOf(toElement);
        if (toElementIndex < 0)
            return new LinkedSet<>();
        return new LinkedSet<>(storage.subList(0, toElementIndex));
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        int fromElementIndex = storage.indexOf(fromElement);
        if (fromElementIndex < 0)
            return new LinkedSet<>();
        return new LinkedSet<>(storage.subList(fromElementIndex, storage.size()));
    }

    @Override
    public T first() {
        return storage.getFirst();
    }

    @Override
    public T last() {
        return storage.getLast();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
