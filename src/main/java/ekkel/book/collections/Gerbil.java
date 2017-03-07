package ekkel.book.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NotSure on 09.03.16.
 */
public class Gerbil {
    private int gerbilNumber;
    private List<Gerbil> gerbilList = null;

    public Gerbil(int gerbilNumber) {
        this.gerbilNumber = gerbilNumber;
    }

    public void hop() {
        System.out.println("Gerbils number: " + gerbilNumber);
    }

    public void listBuilder(int num) {
        gerbilList = new ArrayList<Gerbil>();
        for (int i = 0; i < num; i++) {
            gerbilList.add(new Gerbil((int) Math.round((Math.random()*100))));
        }
    }

    public void get() {
        if (gerbilList!=null) {
            for (Gerbil gerbil : gerbilList) {
                gerbil.hop();
            }
        }
    }


}
