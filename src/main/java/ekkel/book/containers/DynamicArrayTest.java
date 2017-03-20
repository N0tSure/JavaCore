package ekkel.book.containers;

import ekkel.book.util.Generated;
import ekkel.book.util.Generator;
import ekkel.book.util.RandomGenerator;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cresh on 20.03.17.
 */
public class DynamicArrayTest {

    private Generator<String> stringGenerator = RandomGenerator.getStringGenerator(4);
    private List<Test<List<String>>> stringTests = new ArrayList<>();

    @org.junit.Test
    public void test() throws Exception {

        ListTester.run(new SimpleArrayList<>(String.class), stringTests, stringGenerator);
        ListTester.run(new ArrayList<>(), stringTests, stringGenerator);
    }

    @Before
    public void setUp() {
        stringTests.add(new Test<List<String>>("add") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.add(stringGenerator.next());
                    }
                }
                return size * loops;
            }
        });

        stringTests.add(new Test<List<String>>("get") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(Arrays.asList(Generated.toArray(String.class, stringGenerator, size)));
                    for (int j = 0; j < size; j++) {
                        container.get(i);
                    }
                }
                return loops * size;
            }
        });
    }
}
