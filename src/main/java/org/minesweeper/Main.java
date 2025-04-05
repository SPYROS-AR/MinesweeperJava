package org.minesweeper;

import org.minesweeper.model.Board;

import static org.minesweeper.util.Difficulty.*;

public class Main {
    public static void main(String[] args){
        Board board =new Board(EASY);
        board.printBoard(); //DEBUG
    }

}