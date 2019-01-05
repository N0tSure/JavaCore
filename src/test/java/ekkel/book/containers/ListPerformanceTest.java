package ekkel.book.containers;

import ekkel.book.util.*;
import org.junit.Before;
import org.junit.Ignore;

import java.util.*;

/**
 * Created by cresh on 19.03.17.
 */
public class ListPerformanceTest {

    private Random random = new Random();
    private int reps = 1000;
    private Generator<String> generator = RandomGenerator.getStringGenerator(4);
    private List<Test<List<String>>> listTests = new ArrayList<>();
    private List<Test<LinkedList<String>>> queueTests = new ArrayList<>();

    @org.junit.Test
    @Ignore
    public void listPerformanceTest() {
        Tester<List<String>> arrayTest = new Tester<List<String>>(null, listTests.subList(1, 3)) {

            @Override
             protected List<String> initialize(int size) {
                String[] ia = Generated.toArray(String.class, CountingGenerator.getStringGenerator(), size);
                return Arrays.asList(ia);
            }
        };

        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        arrayTest.setTestParams(TestParam.getTestParams(10, 5000, 100, 5000, 1000, 1000, 10000, 200));
        ListTester.run(new ArrayList<>(), listTests, generator);
        ListTester.run(new LinkedList<>(), listTests, generator);
        ListTester.run(new Vector<>(), listTests, generator);
        arrayTest.setFieldWidth(12);

        Tester<LinkedList<String>> qTest = new Tester<>(new LinkedList<>(), queueTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }

    @Before
    public void setUp() {
        listTests.add(new Test<List<String>>("add") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.add(generator.next());
                    }
                }
                return loops * size;
            }
        });

        listTests.add(new Test<List<String>>("get") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops() * reps;
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.get(random.nextInt(size));
                }

                return loops;
            }
        });

        listTests.add(new Test<List<String>>("set") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops() * reps;
                int size = container.size();
                String s = generator.next();
                for (int i = 0; i < loops; i++) {
                    container.set(random.nextInt(size), s);
                }
                return loops;
            }
        });

        listTests.add(new Test<List<String>>("iteradd") {
            @Override
            int test(List<String> container, TestParam testParam) {
                final int LOOPS = 1000000;
                int half = container.size() / 2;
                String s = generator.next();
                ListIterator<String> it = container.listIterator(half);
                for(int i = 0; i < LOOPS; i++)
                    it.add(s);
                return LOOPS;
            }
        });

        listTests.add(new Test<List<String>>("insert") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                String s = generator.next();
                for(int i = 0; i < loops; i++)
                    container.add(5, s); // Minimize random-access cost
                return loops;
            }
        });

        listTests.add(new Test<List<String>>("remove") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(Arrays.asList(Generated.toArray(String.class, generator, size)));
                    while(container.size() > 5)
                        container.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });

        listTests.add(new Test<List<String>>("sort") {
            @Override
            int test(List<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(Arrays.asList(Generated.toArray(String.class, generator, size)));
                    Collections.sort(container);
                }
                return loops;
            }
        });

        queueTests.add(new Test<LinkedList<String>>("addFirst") {
            @Override
            int test(LinkedList<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                String s = generator.next();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addFirst(s);
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<String>>("addLast") {
            @Override
            int test(LinkedList<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                String s = generator.next();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addLast(s);
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<String>>("rmFist") {
            @Override
            int test(LinkedList<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(Arrays.asList(Generated.toArray(String.class, generator, size)));
                    while(container.size() > 0)
                        container.removeFirst();
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<String>>("rmLast") {
            @Override
            int test(LinkedList<String> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(Arrays.asList(Generated.toArray(String.class, generator, size)));
                    while(container.size() > 0)
                        container.removeLast();
                }
                return loops * size;
            }
        });
    }
}
