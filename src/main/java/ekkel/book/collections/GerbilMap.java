package ekkel.book.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by NotSure on 09.03.16.
 */
public class GerbilMap {
    public static void main(String[] args) {
        Map<String,Gerbil> map = new HashMap<String,Gerbil>();
        for (int i = 0; i < 10; i++) {
            map.put(("Gerbil"+i),new Gerbil((int) Math.round((Math.random()*100))));
        }

        Iterator<String> iterator = map.keySet().iterator();

        //Gerbil g;
        while (iterator.hasNext()) {
            map.get(iterator.next()).hop();
        }
    }
}
