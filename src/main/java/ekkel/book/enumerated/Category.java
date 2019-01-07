package ekkel.book.enumerated;

import java.util.EnumMap;

import static ekkel.book.enumerated.Input.*;

/**
 * Created on 07 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum Category {

    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private static EnumMap<Input, Category> categories = new EnumMap<>(Input.class);

    private final Input[] values;

    static {
        for(Category c : Category.class.getEnumConstants())
            for(Input type : c.values)
                categories.put(type, c);
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }

    Category(Input... types) {
        values = types;
    }
}
