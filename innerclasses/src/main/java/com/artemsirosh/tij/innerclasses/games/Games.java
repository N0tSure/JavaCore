package com.artemsirosh.tij.innerclasses.games;

/**
 * Created by cresh on 11.06.16.
 */
class Games {
    public static void play(GameFactory factory) {
        Game game = factory.getGame();
        while (game.move());
    }

    public static void main(String[] args) {
        play(Checkers.getFactory);
        play(Chess.getFactory);
    }
}
