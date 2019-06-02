package com.artemsirosh.tij.tries;

import java.util.AbstractSet;
import java.util.Iterator;

/**
 * <p>
 *     Created on 13.07.2017.
 *
 *     Tries based set implementation
 * </p>
 *
 * @author Artemis A. Sirosh
 */
public class TrieSet extends AbstractSet<String> {

    private Node root;
    private int elementCounter;

    protected TrieSet() {
        this.root = new Node(false);
        this.elementCounter = 0;
    }

    @Override
    public boolean add(String s) {
        root.addChild(s.toUpperCase().toCharArray(), 0, s.length());
        elementCounter++;
        return true;
    }

    @Override
    public boolean contains(Object item) {

        if (item instanceof String) {

            return root.isPresent(((String) item).toUpperCase().toCharArray(), 0, ((String) item).length());
        }
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("You cannot do that");
    }

    @Override
    public int size() {
        return elementCounter;
    }

    @Override
    public void clear() {
        root.clear();
        elementCounter = 0;
    }
}
