package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
public class Pet extends Individual {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Pet> {
        @Override
        public Pet create() {
            return new Pet();
        }
    }
    public Pet(String name) {
        super(name);
    }

    public Pet() {
        super();
    }

    public void speak() {
        System.out.print(this.getClass().getSimpleName() + ": ");
    }
}
