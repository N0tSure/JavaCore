package ekkel.book.collections;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by cresh on 14.03.16.
 */
public class WordsSequence {
    static StringBuilder conseqRepeate(Map<String,ArrayList<Integer>> map) {
        StringBuilder builder = new StringBuilder();
        Map<Integer,String> raw = new TreeMap<Integer, String>();

        for (String word : map.keySet()) {
            for (Integer i : map.get(word)) {
                raw.put(i,word);
            }
        }

        for (Integer integer : raw.keySet()) builder.append(raw.get(integer)+" ");

        return builder;
    }

    static Map<String,ArrayList<Integer>> mapBuilder(StringBuilder builder) {
        Map<String,ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        Integer counter = 0;
        ArrayList<Integer> tmp;

        for (String word : builder.toString().split(" ")) {
            counter++;
            if (map.containsKey(word)) map.get(word).add(counter);
            else map.put(word,new ArrayList<Integer>(Arrays.asList(counter)));
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(args[0]));

            while (true) {
                line = reader.readLine();
                builder.append(line);
                builder.append("\t\n");
                if (line==null) break;
            }
        } catch (FileNotFoundException exc) {
            System.out.println("File not found.");
        } catch (IndexOutOfBoundsException exc) {
            System.out.println("Filename wasn't defined.");
        } catch (Exception exc) {
            System.out.println("Unknown error: " + exc.getMessage());
        } finally {
            try {
                if (reader!=null) reader.close();
            } catch (Exception exc) {
                System.out.println("Error while file closing: " + exc.getMessage());
            }
        }
        Map<String,ArrayList<Integer>> map = mapBuilder(builder);
        System.out.println(map);
        System.out.println();
        System.out.println(builder);
        System.out.println();
        System.out.println(conseqRepeate(map));

    }
}
