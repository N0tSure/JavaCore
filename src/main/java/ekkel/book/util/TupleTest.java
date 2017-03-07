package ekkel.book.util;

/**
 * Created by cresh on 26.08.16.
 */


class TupleTest {
    static TwoTuple<String,Integer> f() {
        return new TwoTuple<>("hi",47);
    }

    static ThreeTuple<Amphibian,String,Integer> g() {
        return new ThreeTuple<>(new Amphibian(),"hi",47);
    }

    static FourTuple<Vehicle,Amphibian,String,Integer> h() {
        return new FourTuple<>(new Vehicle(),new Amphibian(),"hi",47);
    }

    static FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
        return new FiveTuple<>(new Vehicle(), new Amphibian(),"hi",47,3.1415);
    }

    static SixTuple<Vehicle,Amphibian,String,Integer,Double,Object> j() {
        return new SixTuple<>(new Vehicle(),new Amphibian(),"hi",47,3.1415,new Object());
    }

    public static void main(String[] args) {
        TwoTuple<String,Integer>  stringIntegerTwoTuple = f();
        System.out.println(stringIntegerTwoTuple);
        //stringIntegerTwoTuple.first = "there";
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
        System.out.println(j());
    }
}
