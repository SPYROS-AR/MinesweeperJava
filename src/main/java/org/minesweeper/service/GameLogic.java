package org.minesweeper.service;

import org.minesweeper.model.Cell;
import org.minesweeper.model.CellPosition;
import org.minesweeper.model.GameState;
import org.minesweeper.util.Difficulty;

import java.util.Scanner;

public class GameLogic {
    GameState state;

    public void startGame(Difficulty difficulty) {
        state = new GameState();
        state.initializeGame(difficulty);
        state.getBoard().printBoard(); //DEBUG
        System.out.println("Game started with difficulty: " + difficulty);
        while (!state.isGameOver()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter row and column to reveal (e.g., 1 2): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            CellPosition cellPosition = new CellPosition(row, col);
            int result = revealCell(cellPosition);
            System.out.println("Mines remaining: " + state.getRemainingMines());
            if (result == -1) {
                System.out.println("Game Over! You hit a mine.");
                break;
            } else if (result == -2) {
                System.out.println("Congratulations! You won the game.");
                break;
            }
            System.out.println("Revealed cell at (" + row + ", " + col + ") with " + result + " adjacent mines.");
            state.getBoard().printBoard(); //DEBUG

        }
    }

    public int revealCell(CellPosition cellPosition) {
        Cell revealedCell =state.revealCell(cellPosition);
        if(state.isGameOver() && !state.isGameWon()){
            return -1; // Game over
        }
        if (state.isGameWon()){
            return -2; // Game won
        }
        return revealedCell.getAdjacentMines(); // Return the number of adjacent mines
    }
    public int flagCell(CellPosition cellPosition) {
        Cell flaggedCell = state.flagCell(cellPosition);
        if (state.isGameWon()){
            return 1; // Game won
        }
        return 0; // Cell flagged
    }

}
