package org.minesweeper.util;

public enum Difficulty {
    EASY(8, 8, 10),
    MEDIUM(16, 16, 40),
    HARD(16, 30, 99);

    private final int rows;
    private final int cols;
    private final int mines;

    Difficulty(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public int getRows(){return rows;}
    public int getCols(){return cols;}
    public int getMines(){return mines;}
}
