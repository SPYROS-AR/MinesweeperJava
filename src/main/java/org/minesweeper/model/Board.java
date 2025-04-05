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
                board[i][j] = new Cell(new CellPosition((short) i, (short) j));
            }
        }
    }
    private void placeMinesOnBoard(Difficulty difficulty) {
        MinePlacer.placeMines(board, difficulty);
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j].isMine()) {
                    System.out.print("M ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
