package org.minesweeper.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;



public class DifficultyChooser extends JPanel {
    DifficultyButton easyButton, mediumButton, hardButton;
    JLabel titleLabel;
    JPanel buttonPanel;

    public DifficultyChooser() {
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Choose Difficulty", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        initButtons();
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setVisible(true);
    }
    public void initButtons() {
        buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        easyButton = new DifficultyButton("EASY");
        mediumButton = new DifficultyButton("MEDIUM");
        hardButton = new DifficultyButton("HARD");
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        easyButton.addActionListener(actionListener);
        mediumButton.addActionListener(actionListener);
        hardButton.addActionListener(actionListener);
        buttonPanel.add(new JLabel()); // empty cell
        add(buttonPanel, BorderLayout.CENTER);
    }
    ActionListener actionListener = e -> {
        DifficultyButton source = (DifficultyButton) e.getSource();
        String difficulty = source.getDifficulty();
        switch (difficulty) {
            case "EASY":
                System.out.println("Easy difficulty selected");
                break;
            case "MEDIUM":
                System.out.println("Medium difficulty selected");
                break;
            case "HARD":
                System.out.println("Hard difficulty selected");

                break;
        }
    };
}
