package ekkel.book.typeinfo.toys;

/**
 * Created by cresh on 03.08.16.
 */
class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots,HasBrightColors {
    private int amountOfBatteries;
    private int ipSec;
    private int blasterAmount;
    FancyToy() {
        super(1);
    }
}
