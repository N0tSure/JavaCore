package ekkel.book.collections;

import java.util.*;
/**
 * Created by cresh on 07.03.16.
 */
public class Statistics {
    public static void main(String[] args) {
        Random rnd = new Random(47);
        Integer max;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            max=0;
            map.clear();
            for (int r, i = 0; i < 10000; i++) {
                r = rnd.nextInt(20);
                Integer freq = map.get(r);
                map.put(r, freq == null ? 1 : freq + 1);
            }

            for (Integer integer : map.values()) {
                if (integer > max) max = integer;
            }
        System.out.println(map);
            System.out.printf("Integer: %d frequency: %d\n", keyTaker(map, max), max);

    }

    static int keyTaker(Map<Integer,Integer> map, Integer value) {
        Integer keyResult = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key).equals(value)) keyResult=key;
        }
        return keyResult;
    }
}
