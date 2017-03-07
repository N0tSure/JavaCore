package shield.book.loader;

/**
 * Created by cresh on 09.12.16.
 */
public interface Module {
    int EXIT_SUCCESS = 0;
    int EXIT_FAILURE = 1;

    void load();
    void run();
    void unload();
}
