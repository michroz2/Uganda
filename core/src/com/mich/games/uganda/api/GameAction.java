package com.mich.games.uganda.api;


public class GameAction {
    int level;
    int[] sequenceSize; // длины одиночных последовательностей
    int numSequences; // количество одиночных последовательностей
    int showSize; // размер показываемой части последовательности
    int playfieldSize; // размер игрового поля - количество вариантов ответов.
    // Предполагается, что поле это квадрат 3Х3, 4Х4 и т.п. то есть размер = 9, 16, 25
    int correctAnswer; // число от 1 до playfieldSize
    int[][] sequence; // массив самой отгадываемой последовательности. Генерится перед каждым левелом. [element's number][one element]
    int[][] playingSequence; // массив предлагаемых к выбору ответов [playfieldSize][numSequences]
    // - один из элементов должен быть
    int[] points; // очки, получаемые за отгаданные элементы: за один, за последовательный 2-ой, 3-ий и т.д.
    int currentElement; // номер текущего (отгадываемого) элемента

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
     * @return задаёт количество отдельных последовательностей исходя из уровня
     * всегда >=1 и увеличивается.
     */
    int numSequencesByLevel(int level) {
        return (level / 5) + 1; // пока так - надо смотреть по игре
    }

    /**
     * @param level
     * @param sequenceNumber - номер отдельной последовательности
     * @return задаёт длину конкретной отдельной последовательности исходя из уровня
     */
    int sequenceSizeByLevel(int level, int sequenceNumber) {
        int minSize = 3; // Скажем так
        return (level % 5) + (level / 5) + 1 + sequenceNumber; // пока так, потом можно немного порандомайзить
    }
// todo дофига ещё и больше... это только начало

}
