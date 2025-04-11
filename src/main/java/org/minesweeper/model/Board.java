package org.minesweeper.model;

import org.minesweeper.service.MinePlacer;
import org.minesweeper.util.Difficulty;


public class Board {
    private final int rows;
    private final int columns;
    private final int totalMines;
    private final Cell[][] board;


    public Board(Difficulty difficulty) {
        this.rows = difficulty.getRows();
        this.columns = difficulty.getCols();
        this.totalMines = difficulty.getMines();
        this.board = new Cell[rows][columns];
        initializeBoard();
        placeMinesOnBoard(difficulty);
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(new CellPosition( i, j));
            }
        }
    }
    private void placeMinesOnBoard(Difficulty difficulty) {
        MinePlacer.placeMines(this, difficulty);
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].isMine()) {
                    System.out.print("M ");
                } else if (board[i][j].isRevealed()) {
                    System.out.print(board[i][j].getAdjacentMinesNumber() + " ");
                } else if (board[i][j].isFlagged()) {
                    System.out.print("F ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    public Cell revealCell(CellPosition cellPosition) {
        Cell cell = board[cellPosition.getRow()][cellPosition.getColumn()];
        cell.setRevealed(true);
        return cell;
    }

    public Cell[][] getBoard() {
        return board;
    }
    public Cell getCell(CellPosition cellPosition) {
        return board[cellPosition.getRow()][cellPosition.getColumn()];
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
}
