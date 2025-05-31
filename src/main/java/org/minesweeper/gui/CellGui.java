package org.minesweeper.gui;

import org.minesweeper.model.Cell;
import org.minesweeper.service.GameLogic;

import javax.swing.*;
import java.awt.*;


import javax.swing.border.BevelBorder;


public class CellGui extends JPanel {
    private final GameLogic gameLogic;
    private final BoardGUI boardGUI;
    private final CellButton cellButton;
    private final Cell cell;
    JLabel cellLabel;

    public CellGui(Cell cell, GameLogic gameLogic, BoardGUI boardGUI) {
        this.boardGUI = boardGUI;
        this.gameLogic = gameLogic;
        this.cellButton = new CellButton(this);
        this.cell = cell;
        cellLabel = new JLabel("");

        setPreferredSize(new Dimension(30, 30));
        setLayout(new BorderLayout());
        setBackground(new Color(192, 192, 192)); // Classic gray
        setBorder(BorderFactory.createLoweredBevelBorder());

        // Add cellButton initially
        styleButton();
        add(cellButton, BorderLayout.CENTER);
    }

    private void styleButton() {
        cellButton.setBackground(new Color(192, 192, 192));
        cellButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        cellButton.setFocusPainted(false);
        cellButton.setFont(new Font("Dialog", Font.BOLD, 14));
        cellButton.setMargin(new Insets(0, 0, 0, 0));
    }

    public void revealCell() {
        gameLogic.revealCell(cell.getPosition());
        boardGUI.updateCells();
        updateGUIState();
    }

    private void updateGUIState(){
        int state = gameLogic.getGameState().getGameStatus();
        if (state == -1) {
            JOptionPane.showMessageDialog(this, "Game Over! You hit a mine.", "Game Over", JOptionPane.ERROR_MESSAGE);
        } else if (state == 1) {
            JOptionPane.showMessageDialog(this, "Congratulations! You won the game.", "Game Won", JOptionPane.INFORMATION_MESSAGE);
        }
        if (state != 0) {
            boardGUI.resetGame();
        }

    }

    public void showCell() {
        remove(cellButton);
        add(cellLabel, BorderLayout.CENTER);

        cellLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cellLabel.setVerticalAlignment(SwingConstants.CENTER);
        cellLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        if (cell.isMine()) {
            cellLabel.setText("💣"); // or "X"
            setBackground(Color.RED);
        } else {
            int adjacentMines = cell.getAdjacentMinesNumber();
            if (adjacentMines > 0) {
                cellLabel.setText(String.valueOf(adjacentMines));
                cellLabel.setForeground(getClassicNumberColor(adjacentMines));
            } else {
                cellLabel.setText("");
                setBackground(new Color(224, 224, 224)); // lighter gray for revealed
            }
        }
        revalidate();
        repaint();
    }

    public void flagCell() {
        gameLogic.flagCell(cell.getPosition());
        updateCell();
    }

    public void updateCell() {
        SwingUtilities.invokeLater(() -> {
            if (cell.isRevealed()) {
                showCell();
            } else {
                removeAll();
                add(cellButton, BorderLayout.CENTER);
                if (cell.isFlagged()) {
                    cellButton.setText("🚩");
                    cellButton.setForeground(Color.RED);
                } else {
                    cellButton.setText("");
                    cellButton.setForeground(Color.BLACK);
                }
                cellButton.setBackground(new Color(192, 192, 192));
                cellButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            }
            revalidate();
            repaint();
        });
    }

    private Color getClassicNumberColor(int number) {
        return switch (number) {
            case 1 -> Color.BLUE;
            case 2 -> new Color(0, 128, 0);        // Green
            case 3 -> Color.RED;
            case 4 -> new Color(0, 0, 128);        // Navy
            case 5 -> new Color(128, 0, 0);        // Maroon
            case 6 -> new Color(64, 224, 208);
            case 7 -> Color.BLACK;
            case 8 -> Color.DARK_GRAY;
            default -> Color.BLACK;
        };
    }
}
