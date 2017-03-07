package ekkel.book.collections.grep;

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

    static List<WordHolder> counterMatches(StringBuilder builder) {
        List<WordHolder> list = new ArrayList<WordHolder>();
        Set<WordHolder> set = new HashSet<WordHolder>();
        for (String s : builder.toString().split("\\W+")) {
            WordHolder wh = new WordHolder(s);
            if (set.contains(wh)) {
                list.get(list.indexOf(wh)).match();
            } else {
                set.add(wh);
                list.add(wh);
            }
        }
        Collections.sort(list);
        return list;
    }

    static List<String> listBuilder(Set<String> set) {
        List<String> list = new ArrayList<String>(set);
        Collections.sort(list,String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public static void main(String[] args) {
        List<String> list;
        List<WordHolder> wordList;
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

            wordList=counterMatches(stringBuilder);
            //map=mapBuilder(stringBuilder);
            //list=listBuilder(setBuilder(map));
            for (WordHolder w : wordList) {
                System.out.println(w);
            }


        }
    }
}
