package ekkel.book.containers;

import ekkel.book.util.Generator;

/**
 * Created by cresh on 04.03.17.
 */
class Government implements Generator<String> {
    private String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " +
            "government").split(" ");
    private int index;
    @Override
    public String next() { return foundation[index++]; }
}
