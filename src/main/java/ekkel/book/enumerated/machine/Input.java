package ekkel.book.enumerated.machine;

/**
 * Created on 07 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
interface Input {

    int amount();
    Category category();

    enum Category {
        MONEY, ITEM_SELECTION, QUIT_TRANSACTION, SHUT_DOWN
    }
}
