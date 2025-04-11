package org.minesweeper.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellButton extends JButton {
    private CellGui cellGui;
    private boolean isFlagged = false;

    public CellButton(CellGui cellGui) {
        this.cellGui = cellGui;
        setFocusable(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
        setSize(50, 50);
        setFont(cellGui.getFont());
        setBackground(Color.GRAY);
        setVisible(true);
        setButton();
    }

    private void setButton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    isFlagged = !isFlagged;
                    cellGui.flagCell();
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    if (!isFlagged) {
                        cellGui.revealCell();
                    }
                }
            }
        });
    }
}
