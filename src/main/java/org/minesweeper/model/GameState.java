package org.minesweeper.model;

import org.minesweeper.service.MinePlacer;
import org.minesweeper.util.Difficulty;

public class GameState {
    private Board board;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isFirstMove;
    private int remainingMines;

    public GameState() {
        this.isGameOver = false;
        this.isFirstMove = true;
    }

    public void initializeGame(Difficulty difficulty) {
        board = new Board(difficulty);
        remainingMines = difficulty.getMines();
    }

    private void updateRemainingMines(){
        remainingMines--;
    }
    public int getRemainingMines() {
        return remainingMines;
    }
    public Board getBoard() {
        return board;
    }
    public Cell revealCell(CellPosition cellPosition){
        Cell cell = board.revealCell(cellPosition);
        if (cell != null) {
            if (cell.isMine()) {
                if (isFirstMove) {
                    // If it's the first move and the cell is a mine, we need to move the mine
                    MinePlacer.moveMine(board, cellPosition);
                    isFirstMove = false;
                } else {
                    isGameOver = true;
                    return cell; // Game over
                }
            }
            if (isFirstMove) {
                isFirstMove = false;
            }
            floodReveal(cellPosition);
        }
        return cell;
    }
    private void floodReveal(CellPosition position) {
        Cell cell = board.revealCell(position);
        if ( cell.isMine()) {
            return;
        }
        cell.checkAdjacentCells(board.getBoard(), board.getRows(), board.getColumns());
        if (cell.getAdjacentMinesNumber() == 0) {
            for (CellPosition adjacent : cell.getAdjacentCellsPositions()) {
                Cell adjacentCell = board.getBoard()[adjacent.getRow()][adjacent.getColumn()];
                if (!adjacentCell.isRevealed() && !adjacentCell.isMine()) {
                    floodReveal(adjacent); // Recursively reveal this neighbor
                }
            }
        }
    }

    public Cell flagCell(CellPosition cellPosition) {
        Cell cell = board.getBoard()[cellPosition.getRow()][cellPosition.getColumn()];
        if (!cell.isRevealed()) {
            cell.setFlagged(!cell.isFlagged());
            if (cell.isFlagged()) {
                updateRemainingMines();
                updateGameStatus();
            }
        }
        return cell;
    }
    private void updateGameStatus() {
        if (remainingMines == 0) {
            isGameWon = true;
        }
    }
    public boolean isGameWon() {
        return isGameWon;
    }
    public boolean isGameOver() {
        return isGameOver;
    }

}
