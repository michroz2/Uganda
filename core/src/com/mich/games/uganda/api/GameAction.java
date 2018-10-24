package com.mich.games.uganda.api;


import java.util.Random;

/**
 * Класс игровой логики.
 * Как предполагается будет идти геймплэй:
 * 1) Создаётся объект GameAction - при этом инициализируются элементы игры.
 * 2) Интерфейс игры обращается к элементам GameAction для получения и отрисовки:
 * а) отработанной "головы" последовательности, длина которой сначала равна 0, потом увеличивается
 * (тут надо ещё подумать какое максимальное число элементов головы показывать - лучше всю, конечно. Этой логики нет в GameAction)
 * б) игрового поля - некого playfieldSize количества вариантов ответов, один из которых правильный
 * 3) Игрок производит "ход" - выбирая один из вариантов ответов
 * 4) Вызывается метод makeMove, который определяет результат, очки и всё пересчитывает для следующего хода
 * 5) Интерфейс игры опрашивает результат, очки, отрисовывает,
 * узнаёт не закончился ли уровень (возможно есть отдельный контрол, типа "next")
 * 6) игра готова к следующему ходу на этом уровне или первому ходу на следующем уровне.
 */
public class GameAction {
    /*private static*/ int sequenceSizeByLevel[][] = {
            {2},
            {3},
            {4},
            {4, 2},
            {5},
            {3, 2},
            {6},
            {6, 2},
            {6, 3},
            {6, 3, 2},
            {7},
            {5, 2},
            {4, 3},
            {4, 3, 2},
            {6, 4},
            {6, 4, 2},
            {6, 4, 3},
            {6, 4, 3, 2},
            {7, 2},
            {5, 3},
            {5, 4},
            {5, 4, 2},
            {7, 3},
            {7, 4},
            {7, 4, 2},
            {5, 3, 2},
            {6, 5},
            {6, 5, 2},
            {6, 5, 3},
            {6, 5, 3, 2},
            {7, 5},
            {7, 3, 2},
            {7, 6},
            {7, 6, 2},
            {7, 6, 3},
            {7, 6, 3, 2},
            {5, 4, 3},
            {5, 4, 3, 2},
            {6, 5, 4},
            {6, 5, 4, 2},
            {6, 5, 4, 3},
            {6, 5, 4, 3, 2},
            {7, 5, 2},
            {7, 4, 3},
            {7, 4, 3, 2},
            {7, 6, 4},
            {7, 6, 4, 2},
            {7, 6, 4, 3},
            {7, 6, 4, 3, 2},
            {7, 5, 3},
            {7, 5, 4},
            {7, 5, 4, 2},
            {7, 5, 3, 2},
            {7, 6, 5},
            {7, 6, 5, 2},
            {7, 6, 5, 3},
            {7, 6, 5, 3, 2},
            {7, 5, 4, 3},
            {7, 5, 4, 3, 2},
            {7, 6, 5, 4},
            {7, 6, 5, 4, 2},
            {7, 6, 5, 4, 3},
            {7, 6, 5, 4, 3, 2}
    };
    int[] sequenceSize;
    int numSequences;
    int playfieldSize;
    int correctPosition;
    int[][] mainSequence;
    int mainSequenceSize;
    int[][] playfieldSequence;
    int[] points;
    int pointsLevel;
    int pointsTotal;
    int luckyStreak;
    int currentMove;
    int maxMove;
    int maxLevels;
    int maxSequenceSize;
    int level;
    private boolean levelComplete;
    private Random rand;

    /**
     * Конструктор.
     *
     * @param level       - для возможности продолжения игры после Save
     * @param pointsTotal - для возможности продолжения игры после Save
     */
    public GameAction(int level, int pointsTotal, int playfieldSize) {
        this.level = level;
        this.pointsTotal = pointsTotal;
        this.playfieldSize = playfieldSize;
        maxLevels = sequenceSizeByLevel.length;
        rand = new Random();
        createLevel(level);
    }

    void createLevel(int level) {
        numSequences = numSequencesByLevel(level);
        sequenceSize = new int[numSequences];
        for (int i = 0; i < numSequences; i++) {
            sequenceSize[i] = sequenceSizeByLevel(level, i);
        }
        mainSequenceSize = calcMainSequenceSize(sequenceSize);
        mainSequence = new int[numSequences][mainSequenceSize];
        for (int i = 0; i < numSequences; i++) {
            mainSequence[i] = createSingleSequence(sequenceSize[i], mainSequenceSize);
        }
        // set necessary and useful variables for this of level:
        currentMove = 0;
        maxMove = 3 * mainSequenceSize; // максимальное количество ходов для этого уровня, чтобы  уровень не тянулся бесконечно
        points = new int[maxMove];
        pointsLevel = 0;
        levelComplete = false;
        luckyStreak = 0;
        maxSequenceSize = maxSequenceSizeByLevel(level);
        createMove(0);
    }

    void nextLevel() {
        if (++level < maxLevels) {
            createLevel(level);
        }
    }

    /**
     * @param choice - выбор сделанный игроком
     * @return true, если ход был правильным - может использоваться для визуализации успешного хода
     * проверяет верность выбора, начисляет очки, создяёт следующий ход, если надо заканчивает уровень (вопрос: создаёт ли следующий?)
     */
    public boolean makeMove(int choice) {
        boolean result = false;
        if (choice == correctPosition) {  // correct choice
            result = true;
            if (luckyStreak == 0) { // первая отгадка с начала или после неудачи
                points[currentMove] = 1;   // todo: это хардкод - вероятно, нужен класс для начисления очков
            } else { // последовательная отгадка, - удваиваем кол-во очков
                points[currentMove] = points[currentMove - 1] * 2;
            }
            pointsLevel += points[currentMove];
            pointsTotal += points[currentMove];
            luckyStreak++;
            if (luckyStreak == mainSequenceSize) { // это значит отгадана вся последовательность = levelComplete
                levelComplete = true;
            }

        } else {  // incorrect choice
            result = false;
            points[currentMove] = 0;
            luckyStreak = 0;
        }
        if (++currentMove == maxMove) {
            levelComplete = true;
        } else {
            createMove(currentMove);
        }
        return result;
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    /**
     * @param num - номер в диапазоне от 0 до mainSequenceSize
     * @return - один "поперечный" элемент из mainSequence размерности numSequences, то есть:
     * по одному числу из всех отдельных последовательностей.
     * цель - удобство отрисовки элементов игры
     */
    public int[] getElement(int num) {
        int result[] = new int[numSequences];
        for (int i = 0; i < numSequences; i++) {
            result[i] = mainSequence[i][(num % mainSequenceSize)]; // на случай если num будет номером хода > чем длина последовательности
        }
        return result;
    }

    /**
     * @param num - номер в диапазоне от 0 до playfieldSize
     * @return - один "поперечный" элемент из playfieldSequence размерности numSequences, то есть:
     * по одному числу из всех отдельных последовательностей.
     * цель метода - удобство отрисовки элементов игры
     * это практически копия метода getElement -
     */
    public int[] getPlayElement(int num) {
        int result[] = new int[numSequences];
        for (int i = 0; i < numSequences; i++) {
            result[i] = playfieldSequence[i][num];
        }
        return result;
    }

    public int[] getPoints() {
        return points;
    }

    public int getPointsLevel() {
        return pointsLevel;
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    /**
     * @param move - текущий ход
     *             этот метод заполняет playfieldSequence и устанавливает в некий correctPosition правильный элемент, который должен выбрать игрок
     */
    void createMove(int move) {
        int incorrectElement;
        playfieldSequence = new int[numSequences][playfieldSize];
        correctPosition = rand.nextInt(playfieldSize);
        for (int i = 0; i < numSequences; i++) {
            for (int j = 0; j < playfieldSize; j++) { // fill playfield with correct and incorrect elements
                if (j == correctPosition) { // put correct element to correct place
                    playfieldSequence[i][correctPosition] = mainSequence[i][move % mainSequenceSize]; // move может быть больше длины последовательности
                } else {  // put incorrect elements to incorrect places
                    do {
                        incorrectElement = rand.nextInt(mainSequenceSize);
                    }
                    while (incorrectElement == (move % mainSequenceSize)); // move может быть больше длины последовательности
                    playfieldSequence[i][j] = mainSequence[i][incorrectElement];
                }
            }
        }
    }

    public void newGame() {
        level = 0;
        pointsTotal = 0;
        createLevel(0);
    }

    int calcMainSequenceSize(int array[]) { // returns lcm of the array of int values
        int result = 1;
        for (int i = 0; i < array.length; i++) {
            result = lcm(result, array[i]);
        }
        return result;
    }

    int gcd(int a, int b) { // greatest common divider of 2 integers
        return b == 0 ? a : gcd(b, a % b);
    }

    int lcm(int a, int b) { // least common multiple of 2 integers
        return a / gcd(a, b) * b;
    }

    /**
     * @param size
     * @param mainSize
     * @return случайно перемешанную последовательность чисел от 0 до size-1, повторенную до общей длины mainSize
     */
    int[] createSingleSequence(int size, int mainSize) {
        int[] result = new int[mainSize];

        for (int i = 0; i < size; i++) { // заполняем последовательно массив (можно и НЕ последовательно, - это на будущее...)
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
    int numSequencesByLevel(int level) {
        return sequenceSizeByLevel[level].length;
    }

    /**
     * @param level
     * @param sequenceNumber - номер отдельной последовательности
     * @return задаёт длину конкретной отдельной последовательности исходя из уровня
     */
    int sequenceSizeByLevel(int level, int sequenceNumber) {
        return sequenceSizeByLevel[level][sequenceNumber];
    }

    int maxSequenceSizeByLevel(int level) {
        int max = 0;
        for (int i = 0; i < numSequencesByLevel(level); i++) {
            max = (max >= sequenceSizeByLevel(level, i)) ? max : sequenceSizeByLevel(level, i);
        }
        return max;
    }

    public int[] getSequenceSize() {
        return sequenceSize;
    }

    public int getNumSequences() {
        return numSequences;
    }

    public int[][] getMainSequence(int start, int end) {  // This may be useful for showing part of the head of the sequence ?
        if (start > end) {
            return null;
        }
        ;
        int result[][] = new int[numSequences][end - start];
        for (int i = 0; i < numSequences; i++) {
            for (int j = 0; j < (end - start); j++) {
                result[i][j] = mainSequence[i][start + j];
            }
        }
        return result;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public int getMaxLevels() {
        return maxLevels;
    }

    public int getMaxSequenceSize() {
        return maxSequenceSize;
    }

    public int getLevel() {
        return level;
    }
}

