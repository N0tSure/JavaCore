package ekkel.book.innerclasses.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PropertiesLoader {
    private Map<FieldNamingSet,Integer> properties;
    private File file;

    private PropertiesLoader(String fileName) {

    }

    public Map<FieldNamingSet,Integer> getProperties() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            List<String> strings = new ArrayList<>();
            while (reader.read()>0) {
                strings.add(reader.readLine());
            }
            decoder(strings);
        } catch (IOException exc) {
            return null;
        }
        if (properties.size()==8) return properties;
        else return null;
    }

    private void decoder(List<String> lines) {
        for (FieldNamingSet name : FieldNamingSet.values()) {
            String prop = name.getFieldName().concat("=");
            for (String s : lines) {
                if (s.startsWith(prop)) {
                    Integer i = integerExtractor(s);
                    if (i>=0) this.properties.put(name,i);
                    else return;
                }
            }
        }
    }

    private Integer integerExtractor(String line) {
        String[] splited = line.split("=");
        if (splited.length>1) {
            splited[1] = splited[1].trim();
            try {
                return Integer.parseInt(splited[1]);
            } catch (NumberFormatException exc) {
                return -1;
            }
        } else return -1;
    }
}
