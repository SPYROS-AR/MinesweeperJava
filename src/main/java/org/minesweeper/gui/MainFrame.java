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
        setVisible(true);
    }

    private void initComponents() {
        SwingUtilities.invokeLater(() -> {
            getContentPane().removeAll();
            DifficultyChooser chooser = new DifficultyChooser(this);
            add(chooser);
            revalidate();
            repaint();
        });
    }

    public void startGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        GameLogic gameLogic = new GameLogic();
        gameLogic.startGame(difficulty);
        getContentPane().removeAll();
        getContentPane().add(new BoardGUI(gameLogic.getGameState().getBoard(), gameLogic, this), "Center");
        getContentPane().add(new ButtonPanel(this), "South");
        repaint();
        revalidate();
    }

    public void restartGame() {
        initComponents();
    }

}
