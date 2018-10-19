package com.mich.games.uganda.api;


public class GameAction {

    int level;
    int[] sequenceSize;
    int numSequences;
    int showSize;
    int playfieldSize;
    int correctAnswer;
    int[][] sequence;
    int[][] playingSequence;
    int[] points;
    int currentElement;

    public void startGame() {

    }

    public void startLevel(int level) {

    }

    public void makeMove(int move) {

    }

    void generateLevel(int level) {

    }

    /**
     * @param level
     * @return задаёт количество отдельных последовательностей исходя из уровня всегда >=1 и увеличивается.
     */
    int getNumSequencesByLevel(int level) {
        return (level / 5) + 1;
    }

    /**
     * @param level
     * @param sequenceNumber - номер отдельной последовательности
     * @return задаёт длину конкретной отдельной последовательности исходя из уровня
     */
    int getSequenceSizeByLevel(int level, int sequenceNumber) {
        int minSize = 3;
        return (level % 5) + (level / 5) + 1 + sequenceNumber;
    }

}
