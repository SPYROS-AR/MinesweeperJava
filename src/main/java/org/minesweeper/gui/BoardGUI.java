package org.minesweeper.gui;

import org.minesweeper.model.Board;
import org.minesweeper.model.CellPosition;
import org.minesweeper.service.GameLogic;

import javax.swing.*;
import java.awt.*;

public class BoardGUI extends JPanel {
    private GameLogic gameLogic;
    private final int rows;
    private final int cols;
    private final CellGui[][] cells;
    private final Board board;
    private final MainFrame mainFrame;

    public BoardGUI(Board board, GameLogic gameLogic, MainFrame mainFrame) {
        this.board = board;
        this.gameLogic = gameLogic;
        this.mainFrame = mainFrame;
        this.rows = board.getRows();
        this.cols = board.getColumns();
        this.cells = new CellGui[rows][cols];
        setLayout(new GridLayout(rows, cols));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.LIGHT_GRAY);
        initCells();
        setVisible(true);
    }

    private void initCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new CellGui(board.getCell(new CellPosition(i, j)), gameLogic, this);
                add(cells[i][j]);
            }
        }
    }

    public void updateCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j].updateCell();
            }
        }
    }
    public void resetGame() {
        mainFrame.restartGame();
    }
}
