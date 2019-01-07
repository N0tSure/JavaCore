package ekkel.book.enumerated;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum Coins implements Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100);

    private final int amount;

    Coins(int amount) {
        this.amount = amount;
    }

    @Override
    public int amount() {
        return amount;
    }

    @Override
    public Category category() {
        return Category.MONEY;
    }
}
