package com.artemsirosh.tij.containers;

/**
 * Created by cresh on 19.03.17.
 */
class TestParam {
    private final int size;
    private final int loops;

    TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    public int getSize() {
        return size;
    }

    public int getLoops() {
        return loops;
    }

    static TestParam[] getTestParams(int... values) {
        int valuesSize = values.length / 2;
        TestParam[] result = new TestParam[valuesSize];
        int n = 0;
        for (int i = 0; i < valuesSize; i++) {
            result[i] = new TestParam(values[n++], values[n++]);
        }

        return result;
    }

    static TestParam[] getTestParams(String[] values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.decode(values[i]);
        }

        return getTestParams(vals);
    }
}
