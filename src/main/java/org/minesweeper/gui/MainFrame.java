package org.minesweeper.gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public MainFrame() {
        setTitle("Minesweeper");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        setVisible(true);
    }
    private void initComponents() {
        SwingUtilities.invokeLater(() -> {
            DifficultyChooser difficultyChooser = new DifficultyChooser();
            add(difficultyChooser);

        });
    }
}
