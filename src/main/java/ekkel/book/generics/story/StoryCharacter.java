package ekkel.book.generics.story;

/**
 * Created by cresh on 26.08.16.
 */
public class StoryCharacter {
    protected final int id = count++;
    private static int count = 0;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + this.id;
    }
}
