package ekkel.book.containers;

/**
 * Created by cresh on 27.03.17.
 */
import org.junit.Before;

import java.util.*;

public class MapPerfomanceTest {

    private List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();

    @Before
    public void setUp() {
        tests.add(new Test<Map<Integer,Integer>>("put") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.getLoops();
                int size = tp.getSize();
                for(int i = 0; i < loops; i++) {
                    map.clear();
                    for(int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer,Integer>>("get") {
            int test(Map<Integer,Integer> map, TestParam tp) {
                int loops = tp.getLoops();
                int span = tp.getSize() * 2;
                for(int i = 0; i < loops; i++)
                    for(int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });
//        tests.add(new Test<Map<Integer,Integer>>("iterate") {
//            int test(Map<Integer,Integer> map, TestParam tp) {
//                int loops = tp.getLoops() * 10;
//                for(int i = 0; i < loops; i ++) {
//                    Iterator it = map.entrySet().iterator();
//                    while(it.hasNext())
//                        it.next();
//                }
//                return loops * map.size();
//            }
//        });
    }

    @org.junit.Test
    public void testMaps() {
        Tester.getTester(new TreeMap<>(), tests).setFieldWidth(10).setHeadline("TreeMap").timedTest();
        Tester.getTester(new HashMap<>(), tests).setFieldWidth(10).setHeadline("HashMap").timedTest();
        Tester.getTester(new LinkedHashMap<>(), tests).setFieldWidth(10).setHeadline("LinkedHashMap").timedTest();
        Tester.getTester(new IdentityHashMap<>(), tests).setFieldWidth(10).setHeadline("IdentityHashMap").timedTest();
        Tester.getTester(new WeakHashMap<>(), tests).setFieldWidth(10).setHeadline("WeakHashMap").timedTest();
        Tester.getTester(new Hashtable<>(), tests).setFieldWidth(10).setHeadline("Hashtable").timedTest();
    }

    @org.junit.Test
    public void slowMapTest() throws Exception {
        Tester.getTester(new SlowMap<>(), tests).setFieldWidth(10).setHeadline("SlowMap").timedTest();
        Tester.getTester(new HashMap<>(), tests).setFieldWidth(10).setHeadline("HashMap").timedTest();

    }

    @org.junit.Test
    public void simpleHashMapTest() throws Exception {
        Tester.getTester(new SimpleHashMap<>(), tests).setFieldWidth(10).setHeadline("SimpleHashMap").timedTest();
        Tester.getTester(new SimpleHashMap<>(), tests).setFieldWidth(10).setHeadline("SimpleHashMap").timedTest();
        Tester.getTester(new SimpleHashMap<>(), tests).setFieldWidth(10).setHeadline("SimpleHashMap").timedTest();
        Tester.getTester(new HashMap<>(), tests).setFieldWidth(10).setHeadline("HashMap").timedTest();

    }

    @org.junit.Test
    public void loadFactorTest() throws Exception {

        Tester.getTester(new HashMap<>(1600, 0.5f), tests).setFieldWidth(15).setHeadline("HashMap.50").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.6f), tests).setFieldWidth(15).setHeadline("HashMap.60").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.7f), tests).setFieldWidth(15).setHeadline("HashMap.70").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.75f), tests).setFieldWidth(15).setHeadline("HashMap.75").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.80f), tests).setFieldWidth(15).setHeadline("HashMap.80").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.90f), tests).setFieldWidth(15).setHeadline("HashMap.90").timedTest();
        Tester.getTester(new HashMap<>(1600, 0.95f), tests).setFieldWidth(15).setHeadline("HashMap.95").timedTest();

    }
}
