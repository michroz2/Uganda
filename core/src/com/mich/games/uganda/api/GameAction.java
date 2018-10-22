package com.mich.games.uganda.api;


import java.util.Random;

public class GameAction {
    private static final String TAG = "GameAction: ";

    int level;
    int[] sequenceSize;
    int numSequences;
    int showSize;
    int playfieldSize;
    int correctAnswer;
    int[][] sequence;
    int[][] mainSequence;
    int mainSequenceSize;
    int[][] playingSequence;
    int[] points;
    int currentElement;

    public void startGame() {

    }

    public void startLevel(int level) {

    }

    public void makeMove(int move) {

    }

    void createLevel(int level) {
        numSequences = getNumSequencesByLevel(level);
        sequenceSize = new int[numSequences];
        for (int i = 0; i < numSequences; i++) {
            sequenceSize[i] = getSequenceSizeByLevel(level, i);
        }
        mainSequenceSize = calcMainSequenceSize(sequenceSize);
        sequence = new int[numSequences][mainSequenceSize];
        for (int i = 0; i < numSequences; i++) {
            sequence[i] = createSingleSequence(sequenceSize[i], mainSequenceSize);
        }
        int a = 10;
    }

    int calcMainSequenceSize(int array[]) { // returns lcm of the array of int values
        int result = 1;
        for (int i = 0; i < array.length; i++) {
            result = lcm(result, array[i]);
        }
        return result;
    }

    int gcd(int a, int b) { // greatest common divider
        return b == 0 ? a : gcd(b, a % b);
    }

    int lcm(int a, int b) { // least common multiple
        return a / gcd(a, b) * b;
    }

    /**
     * @param size
     * @return случайно перемешанную последовательность чисел от 0 до size-1
     */
    int[] createSingleSequence(int size, int mainSize) {
        int[] result = new int[mainSize];
        Random rand = new Random();

        for (int i = 0; i < size; i++) { // заполняем последовательно массив (можно и НЕ последовательно, на будущее...)
            result[i] = i;
        }
        for (int i = 0; i < size; i++) { // shuffle randomly
            int j = rand.nextInt(size);
            int temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }
        for (int i = 0; i < mainSize; i++) {  // fill with same pattern
            result[i] = result[i % size];
        }
        return result;
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
        return (level % 5) + (level / 5) + 1 + sequenceNumber;
    }

}
