package org.minesweeper.model;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private short adjacentMines;
    private final CellPosition position;

    public Cell(CellPosition position) {
        this.position = position;
        this.isRevealed = false;
        this.isFlagged = false;
        this.adjacentMines = 0;
    }
    public Cell setMine() {
        isMine = true;
        return this;
    }
    public boolean isMine()    {
        return isMine;
    }
}
