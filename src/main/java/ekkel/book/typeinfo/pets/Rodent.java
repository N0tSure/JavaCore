package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
public class Rodent extends Pet {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Rodent> {
        @Override
        public Rodent create() {
            return new Rodent();
        }
    }
    public Rodent(String name) {
        super(name);
    }

    public Rodent() {
        super();
    }

    @Override
    public void speak() {
        super.speak();
        System.out.println("Pi-pi-pi!");
    }
}
