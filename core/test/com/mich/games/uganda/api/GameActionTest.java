package com.mich.games.uganda.api;

import junit.framework.TestCase;

public class GameActionTest extends TestCase {


    public void testStartGame() {
        GameAction gameAction = new GameAction();

        gameAction.startGame();
        gameAction.makeMove(555);
        gameAction.makeMove(666);
        gameAction.makeMove(222);


    }
}