package org.minesweeper.model;

public class CellPosition {
    private final short row;
    private final short column;

    public CellPosition(short row, short column) {
        this.row = row;
        this.column = column;
    }
    public short getRow() {
        return row;
    }
    public short getColumn() {
        return column;
    }

}
