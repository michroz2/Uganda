package com.mich.games.uganda.api;


import java.util.Random;

/**
 * Пример игры.
 * Компьютер закадывает число от 1 до 100. Игрок, получая подсказки, должен за наименьшее кол-во попыток выяснить что это за число
 */
public class GuessNumber {

    private GameProgressListener listener;

    interface GameProgressListener {
        void win(int attempts);

        void less(int number);

        void more(int number);
    }

    private int hiddenNumber;

    private int attemptCount;

    private Random rnd;

    public GuessNumber() {
        rnd = new Random();
    }

    public void startGame(GameProgressListener listener, int maxNum) {
        attemptCount = 0;

        hiddenNumber = rnd.nextInt(maxNum);

        this.listener = listener;
    }

    public void attempt(int number) {
        System.out.println(">>> Is it " + number + "?");
        attemptCount++;

        if (this.hiddenNumber == number) {
            listener.win(attemptCount);
        } else if (this.hiddenNumber < number) {
            listener.less(number);
        } else {
            listener.more(number);
        }
    }
}
