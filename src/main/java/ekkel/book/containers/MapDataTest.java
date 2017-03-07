package ekkel.book.containers;

import ekkel.book.util.CountingGenerator;
import ekkel.book.util.CountingIntegerList;
import ekkel.book.util.FlyweightMap;
import ekkel.book.util.MapData;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by cresh on 04.03.17.
 */
public class MapDataTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapDataTest.class);

    @Test
    public void listsTest() throws Exception {
        List<String> array = new ArrayList<>(FlyweightMap.names(8));
        List<String> linked = new LinkedList<>(FlyweightMap.names(8));
        LOGGER.info("Original arrayList: {}", array);
        LOGGER.info("Original linkedList: {}", linked);

        Collections.sort(array);
        Collections.sort(linked);
        LOGGER.info("Sorted arrayList: {}", array);
        LOGGER.info("Sorted linkedList: {}", linked);

        for (int i = 0; i < 10; i++) {
            Collections.shuffle(array);
            Collections.shuffle(linked);
            LOGGER.info("-----------------------------------");
            LOGGER.info("#{} Shuffled arrayList: {}",i, array);
            LOGGER.info("#{} Shuffled linkedList: {}",i, linked);
        }

    }

    @Test
    public void aCountries() throws Exception {
        Set<String> countriesNames = new HashSet<>();
        for (String name : FlyweightMap.names())
            if (name.charAt(0) == 'A')
                countriesNames.add(name);

        Set<String> anotherWay =
                FlyweightMap.names().stream().filter(s -> s.charAt(0) == 'A').collect(Collectors.toSet());

        LOGGER.info("Simple way: {}", countriesNames);
        LOGGER.info("Alternative way: {}", anotherWay);
        assertTrue(countriesNames.containsAll(anotherWay));
    }

    @Test
    public void countingMapTest() throws Exception {
        LOGGER.info("Counting map : {}", new CountingMapData(30));

    }

    @Test
    public void countingListTest() throws Exception {
        Marker marker = MarkerFactory.getMarker("New marker");
        LOGGER.info(marker, "Counting: {}", new CountingIntegerList(30));

    }

    @Test
    public void flyWeightTest() {
        LOGGER.info(FlyweightMap.capitals().toString());
        LOGGER.info(FlyweightMap.names(10).toString());
        LOGGER.info("{}", new HashMap<>(FlyweightMap.capitals(3)));
        LOGGER.info("{}", new LinkedHashMap<>(FlyweightMap.capitals(3)));
        LOGGER.info("{}", new TreeMap<>(FlyweightMap.capitals(3)));
        LOGGER.info("{}", new Hashtable<>(FlyweightMap.capitals(3)));
        LOGGER.info("{}", new HashSet<>(FlyweightMap.names(6)));
        LOGGER.info("{}", new LinkedHashSet<>(FlyweightMap.names(6)));
        LOGGER.info("{}", new TreeSet<>(FlyweightMap.names(6)));
        LOGGER.info("{}", new ArrayList<>(FlyweightMap.names(6)));
        LOGGER.info("{}", new LinkedList<>(FlyweightMap.names(6)));
        LOGGER.info("{}", FlyweightMap.capitals().get("BRAZIL"));
    }

    @Test
    public void mapDataTest() {
        LOGGER.info(MapData.map(new Letters(), 9).toString());
        LOGGER.info(MapData.map(
                CountingGenerator.getCharacterGenerator(),
                CountingGenerator.getStringGenerator(), 9).toString());
        LOGGER.info(MapData.map(CountingGenerator.getCharacterGenerator(), "Value", 9).toString());
        LOGGER.info(MapData.map(new Letters(), CountingGenerator.getStringGenerator(3)).toString());
        LOGGER.info(MapData.map(new Letters(), "Pop").toString());
    }

    @Test
    public void unsupportedTest() throws Exception {


    }
}
