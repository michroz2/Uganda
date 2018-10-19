package com.mich.games.uganda.api;

import com.mich.games.uganda.api.GuessNumber.GameProgressListener;
import junit.framework.TestCase;

public class GuessNumberTest extends TestCase {

    private int max;
    private int min;

    public void testGame() {

        final GuessNumber game = new GuessNumber();

        max = 1000;
        min = 0;

        game.startGame(new GameProgressListener() {
            @Override
            public void win(int attempts) {
                System.out.println(">>> Yes! Total attempts: " + attempts);
            }

            @Override
            public void less(int number) {
                System.out.println(">>> No, the hidden number is less than " + number + ". Try again.");

                max = number;

                game.attempt(min + (max - min) / 2);
            }

            @Override
            public void more(int number) {
                System.out.println(">>> No, the hidden number is more than " + number + ". Try again.");

                min = number;
                game.attempt(min + (max - min) / 2);
            }
        }, max);


        game.attempt(max / 2); // first attempt
    }
}