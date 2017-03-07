package shield.book.charper13.gen;

/**
 * Created by cresh on 11.04.16.
 */
class GenDemo {
    public static void main(String[] args) {
        Gen<Integer> integerGen;
        integerGen=new Gen<Integer>(88);
        integerGen.showType();
        int value = integerGen.getT();
        System.out.println("Value: " + value);
        System.out.println();

        Gen<String> stringGen = new Gen<String>("Generics testing");
        stringGen.showType();
        String s = stringGen.getT();
        System.out.println("Value: " + s);

        //integerGen = new Gen<Double>(55.0);
        //integerGen=stringGen;

        //Gen<Integer> gen[] = new Gen<Integer>[10];
        Gen<?> gen = new Gen<>(new Object());
    }
}
