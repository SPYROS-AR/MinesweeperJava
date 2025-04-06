package org.minesweeper.gui;

import javax.swing.*;
import java.awt.*;

public class DifficultyButton extends JButton {
    private final String difficulty;

    public DifficultyButton(String difficulty) {
        super(difficulty);
        this.difficulty = difficulty;
        setFocusable(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setSize(200, 50);
        setFont(new Font("Arial", Font.BOLD, 20));
        setVisible(true);
        setColors();
    }
    private void setColors() {
        switch (difficulty) {
            case "EASY":
                setBackground(new Color(0x4CAF50));
                break;
            case "MEDIUM":
                setBackground(new Color(0xFF9800));
                break;
            case "HARD":
                setBackground(new Color(0xF44336));
                break;
        }
    }
    public String getDifficulty() {
        return difficulty;
    }
}
