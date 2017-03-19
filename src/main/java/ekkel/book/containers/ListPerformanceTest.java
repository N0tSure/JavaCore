package ekkel.book.containers;

import ekkel.book.util.CountingGenerator;
import ekkel.book.util.CountingIntegerList;
import ekkel.book.util.Generated;
import org.junit.Before;

import java.util.*;

/**
 * Created by cresh on 19.03.17.
 */
public class ListPerformanceTest {

    private Random random = new Random();
    private int reps = 1000;
    private List<Test<List<Integer>>> listTests = new ArrayList<>();
    private List<Test<LinkedList<Integer>>> queueTests = new ArrayList<>();

    @org.junit.Test
    public void listPerformanceTest() {
        Tester<List<Integer>> arrayTest = new Tester<List<Integer>>(null, listTests.subList(1, 3)) {

            @Override
             protected List<Integer> initialize(int size) {
                Integer[] ia = Generated.toArray(Integer.class, CountingGenerator.getIntegerGenerator(), size);
                return Arrays.asList(ia);
            }
        };

        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        arrayTest.setTestParams(TestParam.getTestParams(10, 5000, 100, 5000, 1000, 1000, 10000, 200));
        ListTester.run(new ArrayList<>(), listTests);
        ListTester.run(new LinkedList<>(), listTests);
        ListTester.run(new Vector<>(), listTests);
        arrayTest.setFieldWidth(12);

        Tester<LinkedList<Integer>> qTest = new Tester<>(new LinkedList<Integer>(), queueTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }

    @Before
    public void setUp() {
        listTests.add(new Test<List<Integer>>("add") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.clear();
                    for (int j = 0; j < size; j++) {
                        container.add(j);
                    }
                }
                return loops * size;
            }
        });

        listTests.add(new Test<List<Integer>>("get") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops() * reps;
                int size = testParam.getSize();
                for (int i = 0; i < loops; i++) {
                    container.get(random.nextInt(size));
                }

                return loops;
            }
        });

        listTests.add(new Test<List<Integer>>("set") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops() * reps;
                int size = container.size();
                for (int i = 0; i < loops; i++) {
                    container.set(random.nextInt(size), 47);
                }
                return loops;
            }
        });

        listTests.add(new Test<List<Integer>>("iteradd") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                final int LOOPS = 1000000;
                int half = container.size() / 2;
                ListIterator<Integer> it = container.listIterator(half);
                for(int i = 0; i < LOOPS; i++)
                    it.add(47);
                return LOOPS;
            }
        });

        listTests.add(new Test<List<Integer>>("insert") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                for(int i = 0; i < loops; i++)
                    container.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });

        listTests.add(new Test<List<Integer>>("remove") {
            @Override
            int test(List<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while(container.size() > 5)
                        container.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });


        queueTests.add(new Test<LinkedList<Integer>>("addFirst") {
            @Override
            int test(LinkedList<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addFirst(47);
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<Integer>>("addLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    for(int j = 0; j < size; j++)
                        container.addLast(47);
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<Integer>>("rmFist") {
            @Override
            int test(LinkedList<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while(container.size() > 0)
                        container.removeFirst();
                }
                return loops * size;
            }
        });

        queueTests.add(new Test<LinkedList<Integer>>("rmLast") {
            @Override
            int test(LinkedList<Integer> container, TestParam testParam) {
                int loops = testParam.getLoops();
                int size = testParam.getSize();
                for(int i = 0; i < loops; i++) {
                    container.clear();
                    container.addAll(new CountingIntegerList(size));
                    while(container.size() > 0)
                        container.removeLast();
                }
                return loops * size;
            }
        });
    }
}
