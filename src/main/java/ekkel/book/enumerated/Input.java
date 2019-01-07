package ekkel.book.enumerated;

import java.util.Random;

/**
 * Created on 07 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum Input {

    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),

    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),

    ABORT_TRANSACTION {
        public int amount() {
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP { // This must be the last instance.
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    private static Random rand = new Random(47);

    private final int value;

    public static Input randomSelection() {
        return values()[rand.nextInt(values().length - 1)];
    }

    Input(int value) {
        this.value = value;
    }

    Input() {
        this.value = 0;
    }

    int amount() {
        return value;
    }
}
