package ekkel.book.generics;

import ekkel.book.util.*;
import static ekkel.book.util.Tuple.*;

/**
 * Created by cresh on 28.08.16.
 */

class TupleTest {
    private static TwoTuple<String,Integer> f() {
        return tuple("hi",47);
    }

    private static TwoTuple ff() {
        return tuple("hi",47);
    }

    private static ThreeTuple<Amphibian,String,Integer> g() {
        return tuple(new Amphibian(),"hi",47);
    }

    private static FourTuple<Vehicle,Amphibian,String,Integer> h() {
        return tuple(new Vehicle(),new Amphibian(),"hi",47);
    }

    private static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
        return tuple(new Vehicle(), new Amphibian(),"hi",47,3.1415);
    }

    private static SixTuple<Vehicle,Amphibian,String,Integer,Double,Long> l() {
        return tuple(new Vehicle(), new Amphibian(),"hi",47,3.1415,4226855012282589L);
    }


    public static void main(String[] args) {
        TwoTuple<String,Integer> twoTuple = f();
        System.out.println(twoTuple);
//        twoTuple = ff();
//        System.out.println(twoTuple);
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
        System.out.println(l());
    }
}
