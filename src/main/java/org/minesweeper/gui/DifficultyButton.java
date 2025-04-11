package org.minesweeper.gui;

import org.minesweeper.util.Difficulty;

import javax.swing.*;
import java.awt.*;

public class DifficultyButton extends JButton {
    private final Difficulty difficulty;
    private final String text;

    public DifficultyButton(Difficulty difficulty) {
        this.text = difficulty.toString();
        this.difficulty = difficulty;
        setText(text);
        setFocusable(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setSize(200, 50);
        setFont(new Font("Arial", Font.BOLD, 20));
        setVisible(true);
        setButton();
    }
    private void setButton() {
        switch (difficulty) {
            case EASY:
                setBackground(new Color(0x4CAF50));
                break;
            case MEDIUM:
                setBackground(new Color(0xFF9800));
                break;
            case HARD:
                setBackground(new Color(0xF44336));
                break;
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
