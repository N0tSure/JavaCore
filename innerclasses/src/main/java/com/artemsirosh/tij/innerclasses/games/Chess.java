package com.artemsirosh.tij.innerclasses.games;

/**
 * Created by cresh on 11.06.16.
 */
class Chess implements Game {
    private Chess() {}
    private int moves = 0;
    private static final int MOVES = 4;

    @Override
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }

    public static GameFactory getFactory = new GameFactory() {
        @Override
        public Game getGame() {
            return new Chess();
        }
    };
}
