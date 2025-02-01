package snakegame.design;

import snakegame.logic.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Snake Chase");
        setLayout(new BorderLayout());

        // game panel positioning in center
        GamePanel gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // button panel positioning right side
        ButtonPanel buttonPanel = new ButtonPanel(gamePanel, this);
        add(buttonPanel, BorderLayout.EAST);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
