package org.minesweeper;


import org.minesweeper.service.GameLogic;

import static org.minesweeper.util.Difficulty.*;

public class Main {
    public static void main(String[] args){
        GameLogic gameLogic = new GameLogic();
        gameLogic.startGame(EASY);
    }

}