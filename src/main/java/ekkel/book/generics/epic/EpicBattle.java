package ekkel.book.generics.epic;

import java.util.List;

/**
 * Created by cresh on 03.12.16.
 */
class EpicBattle {
    static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
    }
    static <POWER extends SuperHearing & SuperSmell> void useSuperFind(SuperHero<POWER> hero) {
        hero.getPower().hearSubtleNoises();
        hero.getPower().trackBySmell();
    }

    public static void main(String[] args) {
        DogBoy dogBoy = new DogBoy();
        useSuperHearing(dogBoy);
        useSuperFind(dogBoy);
        List<? extends SuperHearing> audioBoys;
//        List<? extends SuperHearing & SuperSmell> dogBoys;
    }
}
