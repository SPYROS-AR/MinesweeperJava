package org.minesweeper.service;

import org.minesweeper.model.Cell;
import org.minesweeper.util.Difficulty;

import java.util.Random;

public class MinePlacer {

    public static void placeMines(Cell[][] board, Difficulty difficulty) {
        Random rand = new Random();
        for (int i=0; i< difficulty.getMines(); i++){
            int row;
            int col;
            while(true){
                row = rand.nextInt(difficulty.getRows());
                col = rand.nextInt(difficulty.getCols());
                if (!board[row][col].isMine()) {
                    board[row][col].setMine();
                    System.out.println("Placing mine at: " + row + ", " + col);
                    break;
                }
            }
        }
    }
}
