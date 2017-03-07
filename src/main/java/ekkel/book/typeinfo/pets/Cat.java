package ekkel.book.typeinfo.pets;

/**
 * Created by cresh on 09.08.16.
 */
public class Cat extends Pet {
    static class Factory implements ekkel.book.typeinfo.factory.Factory<Cat> {
        @Override
        public Cat create() {
            return new Cat();
        }
    }

    public Cat(String name) {
        super(name);
    }

    public Cat() {
        super();
    }

    @Override
    public void speak() {
        super.speak();
        System.out.println("Mau-mau!");
    }
}
