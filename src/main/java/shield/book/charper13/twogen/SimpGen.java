package shield.book.charper13.twogen;

/**
 * Created by cresh on 11.04.16.
 */
public class SimpGen {
    public static void main(String[] args) {
        TwoGen<Integer, String> twoGen = new TwoGen<Integer, String>(89,"Generics");
        twoGen.showTypes();

        int t = twoGen.getT();
        System.out.println("Значение: " + t);

        String s = twoGen.getV();
        System.out.println("Значение: " + s);
    }
}
