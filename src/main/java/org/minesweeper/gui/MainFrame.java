package org.minesweeper.gui;

import org.minesweeper.service.GameLogic;
import org.minesweeper.util.Difficulty;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Difficulty difficulty;

    public MainFrame() {
        setTitle("Minesweeper");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        initComponents();
//        startGame(difficulty);
        setVisible(true);
    }

    private void initComponents() {
        SwingUtilities.invokeLater(() -> {
            DifficultyChooser chooser = new DifficultyChooser(this);
            add(chooser);
        });
    }

    public void startGame(Difficulty difficulty) {
        updatePane(difficulty);
    }
    private void restartGame() {
        updatePane(difficulty);
    }
    private void updatePane(Difficulty difficulty) {
        this.difficulty = difficulty;
        GameLogic gameLogic = new GameLogic();
        gameLogic.startGame(difficulty);
        getContentPane().removeAll();
        getContentPane().add(new BoardGUI(gameLogic.getGameState().getBoard(), gameLogic), "Center");
        repaint();
        revalidate();
    }
}
