package com.artemsirosh.tij.collections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by cresh on 11.03.16.
 */
public class WordsCounter {
    static Map<String,Integer> mapBuilder(StringBuilder stringBuilder){
        Map<String,Integer> map = new HashMap<String, Integer>();
        Integer freq = 0;
        for (String s : stringBuilder.toString().split("\\W+")) {
            if (map.containsKey(s)) {
                freq=map.get(s);
                freq+=1;
                map.put(s,freq);
                freq=0;
            } else map.put(s,1);
        }
        return map;
    }

    static Set<String> setBuilder(Map<String,Integer> map) {
        return map.keySet();
    }

    static List<String> listBuilder(Set<String> set) {
        List<String> list = new ArrayList<String>(set);
        Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public static void main(String[] args) {
        List<String> list;
        Map<String,Integer> map;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(args[0]));
        } catch (FileNotFoundException exc) {
            System.out.println("File not found.");
        } catch (IndexOutOfBoundsException exc) {
            System.out.println("Filename wasn't defined.");
        }

        if (reader != null) {
            while (true) {
                try {
                    line = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null) break;
                stringBuilder.append(line);
            }
            map=mapBuilder(stringBuilder);
            list=listBuilder(setBuilder(map));
            for (String s : list) {
                System.out.println(s + " : " + map.get(s));
            }
        }
    }
}
