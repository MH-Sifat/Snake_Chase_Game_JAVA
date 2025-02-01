package snakegame.design;

import snakegame.logic.GamePanel;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public ButtonPanel(GamePanel gamePanel, JFrame parentFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(150, 600));

        // Buttons
        JButton pauseButton = createStyledButton("Pause", Color.LIGHT_GRAY);
        JButton resumeButton = createStyledButton("Resume", Color.BLUE);
        JButton newGameButton = createStyledButton("New Game",Color.BLACK);
        JButton exitButton = createStyledButton("Exit",Color.RED);

        int buttonWidth = 120;
        int buttonHeight = 50;

        // Set normal and maximum size to button
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);
        pauseButton.setPreferredSize(buttonSize);
        pauseButton.setMaximumSize(buttonSize);

        resumeButton.setPreferredSize(buttonSize);
        resumeButton.setMaximumSize(buttonSize);

        newGameButton.setPreferredSize(buttonSize);
        newGameButton.setMaximumSize(buttonSize);

        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);

        // Buttons actions
        pauseButton.addActionListener(e -> gamePanel.pauseGame());
        resumeButton.addActionListener(e -> gamePanel.resumeGame());
        newGameButton.addActionListener(e -> gamePanel.startGame());
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(parentFrame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                parentFrame.dispose(); // Close the current frame
                SwingUtilities.invokeLater(() -> {
                    Home homePage = new Home(); // returning to home page
                    homePage.setVisible(true);
                });

            }
        });

        // Adding buttons
        add(Box.createVerticalStrut(20)); // Spacing
        add(pauseButton);
        add(Box.createVerticalStrut(10));
        add(resumeButton);
        add(Box.createVerticalStrut(10));
        add(newGameButton);
        add(Box.createVerticalStrut(10));
        add(exitButton);
        add(Box.createHorizontalStrut(30)); // Spacing

    }

    //  method to styled button.
    private JButton createStyledButton(String text,Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor); // set background color
        button.setForeground(Color.WHITE); // set font color
        button.setFocusPainted(false); // remove border focus
        button.setBorderPainted(false); // remove button border
        button.setFont(new Font("Arial", Font.BOLD, 14)); // set font style and size
        return button;
    }
}
