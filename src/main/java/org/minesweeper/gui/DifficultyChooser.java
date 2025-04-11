package org.minesweeper.gui;

import org.minesweeper.util.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static org.minesweeper.util.Difficulty.*;


public class DifficultyChooser extends JPanel {
    private DifficultyButton easyButton, mediumButton, hardButton;
    private JLabel titleLabel;
    private JPanel buttonPanel;
    private MainFrame mainFrame;

    public DifficultyChooser(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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
        easyButton = new DifficultyButton(EASY);
        mediumButton = new DifficultyButton(MEDIUM);
        hardButton = new DifficultyButton(HARD);
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
        DifficultyButton source = (DifficultyButton) e.getSource();  // Get the button that was clicked
        Difficulty difficulty = source.getDifficulty();  // Get the difficulty from the button
        mainFrame.startGame(difficulty);  // Start the game with the selected difficulty
    };

}
