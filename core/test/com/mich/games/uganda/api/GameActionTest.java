package com.mich.games.uganda.api;

import junit.framework.TestCase;

import org.junit.Assert;

public class GameActionTest extends TestCase {


    public void testStartGame() {
        GameAction gameAction = new GameAction();

        int a = gameAction.gcd(40, 16);
        Assert.assertTrue(a == 8);

        gameAction.createLevel(1);
        gameAction.createLevel(2);
        gameAction.createLevel(3);
        gameAction.createLevel(6);
        gameAction.createLevel(8);
        gameAction.createLevel(12);
        gameAction.createLevel(15);


/*
        gameAction.startGame();
        gameAction.makeMove(555);
        gameAction.makeMove(666);
        gameAction.makeMove(222);

*/

    }
}