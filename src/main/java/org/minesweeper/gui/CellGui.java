package org.minesweeper.gui;

import org.minesweeper.model.Cell;
import org.minesweeper.service.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CellGui extends JPanel {
    private GameLogic gameLogic;
    private BoardGUI boardGUI;
    private CellButton cellButton;
    private Cell cell;
    JLabel cellLabel;

    public CellGui(Cell cell, GameLogic gameLogic, BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
        this.gameLogic = gameLogic;
        this.cellButton = new CellButton(this);
        this.cell = cell;
        cellLabel=new JLabel("");
        setSize(50, 50);
        setVisible(true);
        setFocusable(false);
        setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
//        add(new JLabel(String.valueOf(cell.getPosition().getRow() + "," + cell.getPosition().getColumn())), "Center");
        add(cellButton, BorderLayout.CENTER);
        setVisible(true);
    }

    public void revealCell() {
        showCell();
        gameLogic.revealCell(cell.getPosition());
        boardGUI.updateCells();
    }
    public void showCell() {
        remove(cellButton);
        add(cellLabel, BorderLayout.CENTER);
        cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cellLabel.setVerticalAlignment(SwingConstants.CENTER);
        cellLabel.setFont(new Font("Arial", Font.BOLD, 40));
        if (cell.isMine()) {
            cellLabel.setText("X");
        } else {
            int adjacentMines = cell.getAdjacentMinesNumber();
            switch (adjacentMines) {
                case 1 -> {
                    cellLabel.setText("1");
                    cellLabel.setForeground(Color.BLUE);
                }
                case 2 -> {
                    cellLabel.setText("2");
                    cellLabel.setForeground(Color.GREEN);
                }
                case 3 -> {
                    cellLabel.setText("3");
                    cellLabel.setForeground(Color.RED);
                }
                case 4 -> {
                    cellLabel.setText("4");
                    cellLabel.setForeground(new Color(0x111184));
                }
                case 5 -> {
                    cellLabel.setText("5");
                    cellLabel.setForeground(new Color(0xA52A2A));
                }
                case 6 -> {
                    cellLabel.setText("6");
                    cellLabel.setForeground(Color.CYAN);
                }
                case 7 -> {
                    cellLabel.setText("7");
                    cellLabel.setForeground(Color.BLACK);
                }
                case 8 -> {
                    cellLabel.setText("8");
                    cellLabel.setForeground(Color.GRAY);
                }
                default -> {
                    cellLabel.setText("");
                    cellLabel.setForeground(Color.LIGHT_GRAY);
                }
            }
        }
        revalidate();
        repaint();
    }
    public void flagCell(){
        gameLogic.flagCell(cell.getPosition());
        updateCell();
    }

    public void updateCell() {
        SwingUtilities.invokeLater(() -> {
            if (cell.isFlagged()) {
                cellButton.setBackground(Color.YELLOW);
                cellButton.setText("F");
            } else {
                cellButton.setBackground(Color.GRAY);
                cellButton.setText("");
            }
            if (cell.isRevealed()) {
                showCell();
            } else {
                removeAll();
                add(cellButton, BorderLayout.CENTER);
                cellButton.setBackground(Color.GRAY);
            }
            revalidate();
            repaint();
        });
    }
}
