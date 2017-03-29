package ekkel.book.containers;

import ekkel.book.util.FlyweightMap;
import ekkel.book.util.TextFile;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cresh on 12.03.17.
 */
public class MapTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapTest.class);


    @Test
    public void simpleHashMapTest() throws Exception {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.putAll(FlyweightMap.capitals(25));
        LOGGER.info("{}", map);
        LOGGER.info("{}", map.get("CONGO"));
        LOGGER.info("{}", map.entrySet());

    }

    @Test
    public void slowMapTest() throws Exception {
        SlowMap<String, String> map = new SlowMap<>();
        map.putAll(FlyweightMap.capitals(15));
        LOGGER.info("Slow map: {}", map);
        LOGGER.info("Capital of Egypt: {}", map.get("EGYPT"));
        LOGGER.info("Entries: {}", map.entrySet());
    }

    @Test
    public void springDetector() throws Exception {
        Map<Groundhog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 10; i++)
            map.put(new Groundhog(i + 1), new Prediction());

        LOGGER.info("All hogs: {}", map);
        Groundhog groundhog = new Groundhog(3);
        LOGGER.info("Looking up prediction for {}", groundhog);
        if (map.containsKey(groundhog))
            LOGGER.info("For {}: {}", groundhog, map.get(groundhog));
        else
            LOGGER.info("Key not found!");

    }

    @Test
    public void wordCounter() throws Exception {
        TextFile textFile = new TextFile("./src/main/resources/asci.sample", "\\W+");
        LOGGER.info("All words: {}", textFile);

        Map<String, Integer> counter = new SimpleHashMap<>();

        for (String word : textFile) {
            Integer count = counter.get(word);
            if (count == null)
                counter.put(word, 1);
            else {
                counter.put(word, count+1);
            }
        }

        LOGGER.info("Result: {}", counter);

    }

    @Test
    public void associativeTest() {
        AssociativeArray<String, String> map = new AssociativeArray<>(6);
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch(ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Too many objects!", e);
        }
        LOGGER.info(map.toString());
        LOGGER.info(map.get("ocean"));
    }

    @Test
    public void maps() throws Exception {
        mapsTest(new SlowMap<>());

    }

    private void mapsTest(Map<Integer, String> map) {
        LOGGER.info(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map has 'Set' behavior for keys:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Producing a Collection of the values:
        LOGGER.info("Values: {}", map.values());
        LOGGER.info(map.toString());
        LOGGER.info("map.containsKey(11): " + map.containsKey(11));
        LOGGER.info("map.get(11): " + map.get(11));
        LOGGER.info("map.containsValue(\"F0\"): " + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        LOGGER.info("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        LOGGER.info("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        // Operations on the Set change the Map:
        map.keySet().removeAll(map.keySet());
        LOGGER.info("map.isEmpty(): " + map.isEmpty());
    }

    private void printKeys(Map<Integer, String> map) {
        LOGGER.info("Size = {}, Keys: {}", map.size(), map.keySet());
    }
}
