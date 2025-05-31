package org.minesweeper.model;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int adjacentMinesCount;
    private final Set<CellPosition> adjacentCellsPositions;
    private final Set<CellPosition> adjacentCellswithoutMines;
    private final CellPosition position;

    public Cell(CellPosition position) {
        this.adjacentMinesCount = 0;
        this.adjacentCellsPositions = new HashSet<>();
        this.adjacentCellswithoutMines = new HashSet<>();
        this.position = position;
        this.isRevealed = false;
        this.isFlagged = false;
        this.isMine = false;
    }

    public Cell setMine(boolean isMine) {
        this.isMine = isMine;
        return this;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public CellPosition getPosition() {
        return position;
    }

    public Set<CellPosition> getAdjacentCellsPositions() {
        return adjacentCellsPositions;
    }

    public Set<CellPosition> getAdjacentCellswithoutMines() {
        return adjacentCellswithoutMines;
    }

    public int getAdjacentMinesNumber() {
        return adjacentMinesCount;
    }

    public void checkAdjacentCells(Cell[][] board, int rows, int cols) {
        adjacentMinesCount = 0;
        adjacentCellsPositions.clear();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int d = 0; d < 8; d++) {
            int newX = position.getRow() + dx[d];
            int newY = position.getColumn() + dy[d];
            if (isValid(newX, newY, rows, cols)) {
                Cell adjacentCell = board[newX][newY];
                adjacentCellsPositions.add(adjacentCell.getPosition());
                if (adjacentCell.isMine()) {
                    adjacentMinesCount++;
                } else {
                    adjacentCellswithoutMines.add(adjacentCell.getPosition());
                }
            }
        }
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}