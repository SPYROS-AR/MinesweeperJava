package org.minesweeper.service;

import org.minesweeper.model.Board;
import org.minesweeper.model.Cell;
import org.minesweeper.model.CellPosition;
import org.minesweeper.util.Difficulty;

import java.util.Random;

public class MinePlacer {

    public static void placeMines(Board board, Difficulty difficulty) {
        for (int i = 0; i < difficulty.getMines(); i++) {
            placeMine(board.getBoard(), difficulty.getRows(), difficulty.getCols());
            System.out.println("Mine # " + i ); //DEBUG
        }
    }

    public static void moveMine(Board board, CellPosition cellPosition) {
        placeMine(board.getBoard(), board.getRows(), board.getColumns()); //find a new position for the mine
        // Remove the mine from the old position
        Cell cell = board.getBoard()[cellPosition.getRow()][cellPosition.getColumn()];
        cell.setMine(false);
    }

    private static void placeMine(Cell[][] board, int rows, int cols) {
        Random rand = new Random();
        int row, col;
        // Keep trying until we find an empty cell
        while (true) {
            row = rand.nextInt(rows);
            col = rand.nextInt(cols);
            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                System.out.println("Placing mine at: " + row + ", " + col ); //DEBUG
                break;
            }
        }
    }
}
