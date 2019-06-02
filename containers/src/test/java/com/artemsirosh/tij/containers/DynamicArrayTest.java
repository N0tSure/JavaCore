package com.artemsirosh.tij.containers;

import com.artemsirosh.tij.util.Generated;
import com.artemsirosh.tij.util.Generator;
import com.artemsirosh.tij.util.RandomGenerator;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by cresh on 20.03.17.
 */
public class DynamicArrayTest {

    private Generator<String> stringGenerator = RandomGenerator.getStringGenerator(4);
    private List<Test<List<String>>> stringTests = new ArrayList<>();
    private Random random = new Random();

    @org.junit.Test
    public void test() throws Exception {

        ListTester.run(new ArrayList<>(), stringTests, stringGenerator);
        ListTester.run(new SimpleArrayList<>(String.class), stringTests, stringGenerator);
    }

    @Before
    public void setUp() {
        stringTests.add(new Test<List<String>>("add") {
            @Override
            int test(List<String> container, TestParam testParam) {
                for (int i = 0; i < testParam.getLoops(); i++) {
                    container.clear();
                    for (int j = 0; j < testParam.getSize(); j++) {
                        container.add(stringGenerator.next());
                    }
                }
                return testParam.getLoops() * testParam.getSize();
            }
        });

        stringTests.add(new Test<List<String>>("get") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                container.clear();
                container.addAll(Arrays.asList(Generated.toArray(String.class, stringGenerator, size)));
                for (int i = 0; i < loops; i++) {
                    container.get(random.nextInt(size));
                }
                return loops;
            }
        });
    }
}
