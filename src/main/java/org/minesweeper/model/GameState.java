package org.minesweeper.model;

import org.minesweeper.service.GameLogic;
import org.minesweeper.service.MinePlacer;
import org.minesweeper.util.Difficulty;

import java.util.LinkedList;
import java.util.Queue;

public class GameState {
    private Board board;
    private GameLogic gameLogic;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isFirstMove;
    private int remainingMines;

    public GameState(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.isGameOver = false;
        this.isFirstMove = true;
    }

    public void initializeGame(Difficulty difficulty) {
        board = new Board(difficulty);
        remainingMines = difficulty.getMines();
    }

    private void updateRemainingMines() {
        remainingMines--;
    }

    public int getRemainingMines() {
        return remainingMines;
    }

    public Board getBoard() {
        return board;
    }

    public Cell revealCell(CellPosition cellPosition) {
        Cell cell = board.getCell(cellPosition);
        if (cell.isMine()) {
            if (isFirstMove) {
                // If it's the first move and the cell is a mine, we need to move the mine
                MinePlacer.moveMine(board, cellPosition);
            } else {
                isGameOver = true;
                return cell; // Game over
            }
        }
        if (isFirstMove) {
            isFirstMove = false;
        }
        floodReveal(cellPosition);
        return cell;
    }

    private void floodReveal(CellPosition position) {
        Cell cell = board.getBoard()[position.getRow()][position.getColumn()];
        cell.setRevealed(true);
        cell.checkAdjacentCells(board.getBoard(), board.getRows(), board.getColumns());
        if (cell.getAdjacentMinesNumber() == 0) {
            for (CellPosition adjacent : cell.getAdjacentCellswithoutMines()) {
                Cell adjacentCell = board.getBoard()[adjacent.getRow()][adjacent.getColumn()];
                if (!adjacentCell.isRevealed()) {
                    System.out.println("Flooding to: " + adjacent.getRow() + ", " + adjacent.getColumn());
                    floodReveal(adjacent); // Recursively reveal this neighbor
                }
            }
        }
    }

    public void flood(CellPosition position) {

        Queue<CellPosition> queue = new LinkedList<>();
        queue.add(position);
        while (!queue.isEmpty()) {
            System.out.println("inside flood");
            // Get the next cell position from the queue
            CellPosition currentPosition = queue.poll();
            Cell currentCell = board.getBoard()[currentPosition.getRow()][currentPosition.getColumn()];
            // Skip if the cell is already revealed
            if (currentCell.isRevealed()) {
                continue;
            }
            // Reveal the current cell
            if (!currentCell.isFlagged()) {
                currentCell.setRevealed(true);
            }
            System.out.println("Flooding to: " + currentPosition.getRow() + ", " + currentPosition.getColumn());
            currentCell.checkAdjacentCells(board.getBoard(), board.getRows(), board.getColumns());
            // If the current cell has no adjacent mines, add its adjacent cells to the queue
            if (currentCell.getAdjacentMinesNumber() == 0) {
                for (CellPosition adjacent : currentCell.getAdjacentCellswithoutMines()) {
                    // Check if the adjacent cell has not been revealed yet
                    Cell adjacentCell = board.getBoard()[adjacent.getRow()][adjacent.getColumn()];
                    if (!adjacentCell.isRevealed() && !adjacentCell.isFlagged()) {
                        queue.add(adjacent); // Add adjacent cell to the queue
                    }
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
