package ekkel.book.generics.epic;

/**
 * Created by cresh on 03.12.16.
 */
class DogBoy extends CanineHero<SuperHearSmell> {
    public DogBoy() {
        super(new SuperHearSmell());
    }
}
