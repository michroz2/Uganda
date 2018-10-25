package com.mich.games.uganda.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mich.games.uganda.UgandaGame;
import com.mich.games.uganda.api.GameAction;

import java.util.Scanner;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1027;
        config.height = 768;
        new LwjglApplication(new UgandaGame(), config);
        playFullGameOnConsole();
    }

    static void playFullGameOnConsole() { // Play full game with console interaction
        int level = 0;
        int pointsInitial = 0;
        int playFieldSize = 5;
        Scanner scanner = new Scanner(System.in);

        GameAction gameAction = new GameAction(level, pointsInitial, playFieldSize);


        System.out.println("=======Start testPlayFullGameRandomAuto =======");
        System.out.println("Max Levels: " + gameAction.getMaxLevels());
        System.out.print("Start with level number :");
        level = scanner.nextInt();
        gameAction.createLevel(level);

        while (level < gameAction.getMaxLevels()) {

            System.out.println("==============");
            System.out.println("Level started: " + level);
            System.out.print("Sequences: " + gameAction.getNumSequences());
            System.out.println("; Sequence size: " + gameAction.getMainSequenceSize());

            while (!gameAction.isLevelComplete()) {
                // В реальной игре тут отрисовывается красивое игровое поле согласно текущим данным объекта gameAction
                int curMove = gameAction.getCurrentMove();
                int maxHead = gameAction.getMaxHeadSize();
                int startHead = (curMove > maxHead) ? curMove - maxHead : 0;

                System.out.print("Known head of the sequence");  // Выводим крайний кусок отработанной "головы" последовательности:
                if (startHead > 0) {
                    System.out.println(" (last " + maxHead + " elements):");
                } else {
                    System.out.println(" (from the start):");
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

                int move = scanner.nextInt(); // Делаем ход. Не проверяется!!

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
            System.out.println("Level complete: " + level);
            System.out.print("Continue? Y/n: ");
            String res = scanner.nextLine();
            if (res.startsWith("n") || res.startsWith("N")) { break;}

            gameAction.nextLevel();
            level = gameAction.getLevel();
        }  // Конец цикла по уровням

        System.out.println("======= GAME OVER! =======");

    }

}
