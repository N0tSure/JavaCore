package ekkel.book.containers;

import org.junit.Before;

import java.util.*;

/**
 * Created by cresh on 26.03.17.
 */
public class SetPerformanceTest {

    private List<Test<Set<Integer>>> tests = new ArrayList<>();

    @org.junit.Test
    public void setPerformance() {
        Tester.getTester(new TreeSet<>(), tests).setFieldWidth(10).setHeadline("TreeSet").timedTest();
        Tester.getTester(new HashSet<>(), tests).setFieldWidth(10).setHeadline("HashSet").timedTest();
        Tester.getTester(new LinkedHashSet<>(), tests).setFieldWidth(10).setHeadline("LinkedHashSet").timedTest();
    }

    @Before
    public void setUp() {
        tests.add(new Test<Set<Integer>>("add") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.getLoops();
                int size = tp.getSize();
                for(int i = 0; i < loops; i++) {
                    set.clear();
                    for(int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Set<Integer>>("contains") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.getLoops();
                int span = tp.getSize() * 2;
                for(int i = 0; i < loops; i++)
                    for(int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });
        tests.add(new Test<Set<Integer>>("iterate") {
            int test(Set<Integer> set, TestParam tp) {
                int loops = tp.getLoops() * 10;
                for(int i = 0; i < loops; i++) {
                    Iterator<Integer> it = set.iterator();
                    while(it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }
}
