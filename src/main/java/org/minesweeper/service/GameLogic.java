package org.minesweeper.service;

import org.minesweeper.model.Cell;
import org.minesweeper.model.CellPosition;
import org.minesweeper.model.GameState;
import org.minesweeper.util.Difficulty;


public class GameLogic {
    GameState state;


    public void startGame(Difficulty difficulty) {
        state = new GameState(this);
        state.initializeGame(difficulty);
    }

    public GameState getGameState() {
        return state;
    }

    public int revealCell(CellPosition cellPosition) {
        Cell revealedCell = state.revealCell(cellPosition);
        if (state.isGameOver() && !state.isGameWon()) {
            return -1; // Game over
        }
        if (state.isGameWon()) {
            return -2; // Game won
        }
        return revealedCell.getAdjacentMinesNumber(); // Return the number of adjacent mines
    }

    public int flagCell(CellPosition cellPosition) {
        Cell flaggedCell = state.flagCell(cellPosition);
        if (state.isGameWon()) {
            return 1; // Game won
        }
        return 0; // Cell flagged
    }

}
