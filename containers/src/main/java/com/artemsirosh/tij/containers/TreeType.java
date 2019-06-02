package com.artemsirosh.tij.containers;

/**
 * Created by cresh on 11.03.17.
 */
class TreeType extends SetType implements Comparable<TreeType> {
    public TreeType(int i) {
        super(i);
    }

    @Override
    public int compareTo(TreeType o) {
        return Integer.compare(o.i, this.i);
    }
}
