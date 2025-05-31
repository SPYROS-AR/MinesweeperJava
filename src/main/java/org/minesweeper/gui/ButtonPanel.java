package org.minesweeper.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    MainFrame mainFrame;
    public ButtonPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.LIGHT_GRAY);
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(actionListener);
        add(restartButton, "Center");
    }
    ActionListener actionListener = e -> {
        mainFrame.restartGame();
    };
}