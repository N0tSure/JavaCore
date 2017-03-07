package ekkel.book.innerclasses.games;

/**
 * Created by cresh on 11.06.16.
 */
class Checkers implements Game{
    private Checkers() {
        //no-op
    }
    private int moves = 0;
    private static final int MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }

    public static GameFactory getFactory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Checkers();
        }
    };

}
