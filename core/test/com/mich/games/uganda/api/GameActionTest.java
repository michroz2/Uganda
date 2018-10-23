package com.mich.games.uganda.api;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class GameActionTest extends TestCase {

    @Test
    public void testStartGame() {
        int level = 0;
        int pointsTotal = 0;
        int playFieldSize = 5;
        GameAction gameAction = new GameAction(level, pointsTotal, playFieldSize);

        int a = gameAction.gcd(40, 16);
        Assert.assertTrue(a == 8);

        gameAction.createLevel(1);
        gameAction.createLevel(2);
        gameAction.createLevel(3);
        gameAction.createLevel(6);
        gameAction.createLevel(8);
        gameAction.createLevel(12);
        gameAction.createLevel(15);


    }

    @Test
    public void testOneLevelSequence() {
        int level = 3;
        int pointsInitial = 0;
        int playFieldSize = 5;
        Random rand = new Random();

        GameAction gameAction = new GameAction(level, pointsInitial, playFieldSize);

        System.out.println("=======Start TestOneLevelSequence=======");
        System.out.println("Level started: " + level);
        System.out.println("Sequences: " + gameAction.getNumSequences());
        System.out.println("Sequence size: " + gameAction.mainSequenceSize);

        while (!gameAction.isLevelComplete()) {
            // В реальной игре тут отрисовывается красивое игровое поле согласно текущим данным объекта gameAction
            System.out.println("Known sequence so far: ");
            for (int i = 0; i < gameAction.getCurrentMove(); i++) {
                System.out.print(i+": |");
                int[] element = gameAction.getElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j]+"|");
                }
                System.out.println();
            }

            System.out.println("Make your choice: ");
            for (int i = 0; i < playFieldSize; i++) {
                System.out.print(i+": |");
                int[] element = gameAction.getPlayElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j]+"|");
                }
                System.out.println();
            }

            int move = rand.nextInt(playFieldSize); // Делаем случайный ход. В реальной игре - игрок сам выбирает один вариант из предложенных

            // Остальные действия в этом ходе - это отображение результата
            System.out.println("Move #" + gameAction.getCurrentMove() + "; " +
                    "Correct=" + gameAction.correctPosition + "; " +
                    "Played=" + move + "; " +
                    "Result: " + gameAction.makeMove(move) + "; " +
                    "Points: " + gameAction.getPointsTotal());
        }
        System.out.println("Level сomplete: " + level);
        System.out.println("=======Endof TestOneLevelSequence=======");
    }
}