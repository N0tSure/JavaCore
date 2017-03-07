package ekkel.book.typeinfo.factory;

/**
 * Created by cresh on 10.08.16.
 */
class RegisteredFactories {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Part.createRandom());
        }
    }
}
