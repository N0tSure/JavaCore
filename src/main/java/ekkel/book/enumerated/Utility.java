package ekkel.book.enumerated;

/**
 * Created on 08 Jan, 2019.
 *
 * @author Artemis A. Sirosh
 */
enum Utility implements Input {

    ABORT_TRANSACTION(Input.Category.QUIT_TRANSACTION), STOP(Input.Category.SHUT_DOWN);

    private final Input.Category category;

    Utility(Input.Category category) {
        this.category = category;
    }

    @Override
    public int amount() {
        throw new UnsupportedOperationException(this.name() + " not support amount() operation.");
    }

    @Override
    public Category category() {
        return category;
    }}
