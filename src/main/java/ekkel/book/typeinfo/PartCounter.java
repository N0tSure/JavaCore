package ekkel.book.typeinfo;

import ekkel.book.typeinfo.factory.Part;

/**
 * Created by cresh on 10.08.16.
 */
public class PartCounter {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Part.class);
        for (int i = 0; i < 20; i++) {
            Part part;
            try {
                part = Part.createPart();
            } catch (InstantiationException | IllegalAccessException exc) {
                throw new RuntimeException(exc);
            }
            System.out.printf("%s ",part.getClass().getSimpleName());
            counter.count(part);
        }
        System.out.println();
        System.out.println(counter);
    }
}
