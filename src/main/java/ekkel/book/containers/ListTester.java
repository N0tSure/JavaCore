package ekkel.book.containers;

import ekkel.book.util.CountingIntegerList;

import java.util.List;

/**
 * Created by cresh on 19.03.17.
 */
class ListTester extends Tester<List<Integer>> {

    static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
        new ListTester(list, tests).timedTest();
    }

    ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
        super(container, tests);
        this.setHeadline(container.getClass().getSimpleName() + " tests");
    }

    @Override
    List<Integer> initialize(int size) {
        container.clear();
        container.addAll(new CountingIntegerList(size));
        return container;
    }
}
