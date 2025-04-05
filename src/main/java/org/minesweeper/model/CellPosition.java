package org.minesweeper.model;

public class CellPosition {
    private final int row;
    private final int column;

    public CellPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

}
