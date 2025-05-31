package org.minesweeper.model;

import org.minesweeper.service.GameLogic;
import org.minesweeper.service.MinePlacer;
import org.minesweeper.util.Difficulty;


public class GameState {
    private Board board;
    private GameLogic gameLogic;
    private boolean isGameOver;
    private boolean isGameWon;
    private boolean isFirstMove;


    public GameState(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.isGameOver = false;
        this.isFirstMove = true;
    }

    public void initializeGame(Difficulty difficulty) {
        board = new Board(difficulty);
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
                cell.setRevealed(true);
                getGameStatus();
                return cell; // Game over
            }
        }
        if (isFirstMove) {
            isFirstMove = false;
        }
        floodReveal(cellPosition);
        getGameStatus();
        return cell;
    }
    public int getGameStatus(){
        if (isGameOver){
            return -1; // Game over
        }
        int revealedCells = 0;
        int totalCells = board.getRows() * board.getColumns();
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                Cell cell = board.getBoard()[i][j];
                if (cell.isRevealed() && !cell.isMine()) {
                    revealedCells++;
                }
            }
        }
        if (revealedCells == totalCells - board.getTotalMines()) {
            isGameWon = true;
            return 1; // Game won
        }
        return 0; // Game still ongoing
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

    public Cell flagCell(CellPosition cellPosition) {
        Cell cell = board.getBoard()[cellPosition.getRow()][cellPosition.getColumn()];
        if (!cell.isRevealed()) {
            cell.setFlagged(!cell.isFlagged());
        }
        return cell;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

}