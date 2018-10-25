package com.mich.games.uganda.api;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

public class GameActionTest extends TestCase {

    @Test
    public void testScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt()); // Не работает...
    }

    /**
     * Этот тест должен произвести в консоли точную копию массива GameAction.sequenceSizeByLevel[][],
     * полученную через соответствующие методы.
     * А в случае замены этой методы на что-то другое - показать аналогичный результат
     */
    @Test
    public void testSequences() {
        GameAction gameAction = new GameAction(0, 0, 10);

        System.out.println("======= Start testSequences =======");
        for (int i = 0; i < gameAction.getMaxLevels(); i++) {
            int numSequences = gameAction.numSequencesByLevel(i);
            System.out.print("{");
            for (int j = 0; j < numSequences; j++) {
                //if (j>0) {System.out.print(", ");};
                System.out.print((j > 0) ? ", " : "");
                System.out.print(gameAction.sequenceSizeByLevel(i, j));
            }
            System.out.println("},");
        }
    }

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
    public void testPlayOneLevelRandomAuto() { // Play one level in console
        int level = 7;
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
            System.out.println("Known head of the sequence so far: ");  // Сначала рисуем отработанную "голову" последовательности:
            for (int i = 0; i < gameAction.getCurrentMove(); i++) {
                System.out.print(i + ": |");
                int[] element = gameAction.getElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j] + "|");
                }
                System.out.println();
            }

            System.out.println("Make your choice (" + gameAction.correctPosition + "):");  // Теперь отрисовываем игровое поле с вариантами ответа:
            for (int i = 0; i < playFieldSize; i++) {
                System.out.print(i + ": |");
                int[] element = gameAction.getPlayElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j] + "|");
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

    @Test
    public void testPlayFullGameRandomAuto() { // Play full game with random moves, output to console
        int level = 0;
        int pointsInitial = 0;
        int playFieldSize = 5;
        Random rand = new Random();
        GameAction gameAction = new GameAction(level, pointsInitial, playFieldSize);


        System.out.println("=======Start testPlayFullGameRandomAuto =======");
        System.out.println("Max Levels: " + gameAction.getMaxLevels());

        while (level < gameAction.getMaxLevels()) {

            System.out.println("==============");
            System.out.println("Level started: " + level);
            System.out.println("Sequences: " + gameAction.getNumSequences());
            System.out.println("Sequence size: " + gameAction.mainSequenceSize);

            while (!gameAction.isLevelComplete()) {
                // В реальной игре тут отрисовывается красивое игровое поле согласно текущим данным объекта gameAction
                int curMove = gameAction.getCurrentMove();
                int maxSequence = gameAction.getMaxSequenceSize();
                int startHead = (curMove > maxSequence) ? curMove - maxSequence : 0;

                System.out.print("Known head of the sequence");  // Сначала рисуем последний кусок отработанной "головы" последовательности:
                if (startHead > 0) {
                    System.out.println(" (last " + (curMove - startHead) + " elements):");
                } else {
                    System.out.println(":");
                }

                for (int i = startHead; i < curMove; i++) {
                    System.out.print(i + ": |");
                    int[] element = gameAction.getElement(i);
                    for (int j = 0; j < element.length; j++) {
                        System.out.print(element[j] + "|");
                    }
                    System.out.println();
                }

                System.out.println("Make your choice (" + gameAction.correctPosition + "):");  // Теперь отрисовываем игровое поле с вариантами ответа:
                for (int i = 0; i < playFieldSize; i++) {
                    System.out.print(i + ": |");
                    int[] element = gameAction.getPlayElement(i);
                    for (int j = 0; j < element.length; j++) {
                        System.out.print(element[j] + "|");
                    }
                    System.out.println();
                }

                int move = rand.nextInt(playFieldSize); // Делаем случайный ход. В реальной игре - игрок сам выбирает один вариант из предложенных

                // Остальные действия в этом ходе - это отображение результата
                System.out.println(
                        "Level #" + level + "; " +
                                "Move #" + gameAction.getCurrentMove() + "; " +
                                "Correct #" + gameAction.correctPosition + "; " +
                                "Played #" + move + "; " +
                                "Result: " + gameAction.makeMove(move) + "; " +
                                "LevelPoints: " + gameAction.getPointsLevel() + "; " +
                                "TotalPoints: " + gameAction.getPointsTotal());
            }
            System.out.println("Level сomplete: " + level);

            gameAction.nextLevel();
            level = gameAction.getLevel();
        }  // Конец цикла по уровням

        System.out.println("======= GAME OVER! =======");

    }

    @Test
    public void testPlayOneLevelRealOnConsole() { // Play one level in console
        int level = 3;
        int pointsInitial = 0;
        int playFieldSize = 5;

        GameAction gameAction = new GameAction(level, pointsInitial, playFieldSize);

        System.out.println("=======Start TestOneLevelSequence=======");
        System.out.println("Level started: " + level);
        System.out.println("Sequences: " + gameAction.getNumSequences());
        System.out.println("Sequence size: " + gameAction.mainSequenceSize);

        // Далее идёт цикл игры пока не закончится уровень
        while (!gameAction.isLevelComplete()) {
            // В реальной игре отрисовывается игровое поле согласно текущим данным объекта gameAction.
            System.out.println("Known sequence so far: ");  // Сначала выводим отработанную "голову" последовательности:
            for (int i = 0; i < gameAction.getCurrentMove(); i++) {
                System.out.print(i + ": |");
                int[] element = gameAction.getElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j] + "|");
                }
                System.out.println();
            }

            System.out.println("Make your choice: ");  // Теперь отрисовываем игровое поле с вариантами ответа:
            for (int i = 0; i < playFieldSize; i++) {
                System.out.print(i + ": |");
                int[] element = gameAction.getPlayElement(i);
                for (int j = 0; j < element.length; j++) {
                    System.out.print(element[j] + "|");
                }
                System.out.println();
            }

            Scanner input = new Scanner(System.in);
            System.out.print("Your choice: ");
            int move = input.nextInt(); // Делаем ход способом ввода целого значения варианта (из предложенных)

            // Остальные действия в этом ходе - это отображение результата
            System.out.println("Move #" + gameAction.getCurrentMove() + "; " +
                    "Correct=" + gameAction.correctPosition + "; " +
                    "Played=" + move + "; " +
                    "Result: " + gameAction.makeMove(move) + "; " +
                    "Points: " + gameAction.getPointsTotal());
        }
// Вывод сообщения об окончании уровня:
        System.out.println("Level сomplete: " + level);
        System.out.println("=======Endof TestOneLevelSequence=======");
    }
}