package ekkel.book.containers;

import ekkel.book.util.CountingIntegerList;
import ekkel.book.util.Generated;
import ekkel.book.util.Generator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cresh on 19.03.17.
 */
class ListTester extends Tester<List<String>> {

    static void run(List<String> list, List<Test<List<String>>> tests, Generator<String> generator) {
        new ListTester(list, tests, generator).timedTest();
    }

    private Generator<String> generator;

    ListTester(List<String> container, List<Test<List<String>>> tests, Generator<String> generator) {
        super(container, tests);
        this.setHeadline(container.getClass().getSimpleName() + " tests");
        this.generator = generator;
    }

    @Override
    List<String> initialize(int size) {
        container.clear();
        container.addAll(Arrays.asList(Generated.toArray(String.class, generator, size)));
        return container;
    }
}
