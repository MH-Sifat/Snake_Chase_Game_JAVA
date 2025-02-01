package snakegame.design;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    public Home() {
        // Frame design
        setTitle("Snake Chase");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // snake game Main panel
        JPanel mainPanel = new JPanel() {

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Home page background image
                ImageIcon background = new ImageIcon("Images/background.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        mainPanel.setLayout(null);

        // Start Game Button
        JButton startGameButton = new JButton("START GAME");
        startGameButton.setBackground(Color.RED);
        startGameButton.setForeground(Color.WHITE);
        startGameButton.setFont(new Font("Arial", Font.BOLD, 18));
        startGameButton.setFocusPainted(false);
        startGameButton.setBounds(300, 200, 200, 50);
        startGameButton.addActionListener(e -> {
            dispose(); // Close page
            SwingUtilities.invokeLater(() -> {
                GameFrame gameFrame = new GameFrame();
                gameFrame.setVisible(true);
            });
        });

        // Premium Membership Button
        JButton premiumButton = new JButton("Premium Membership");
        premiumButton.setBackground(Color.YELLOW);
        premiumButton.setForeground(Color.BLACK);
        premiumButton.setFont(new Font("Arial", Font.BOLD, 16));
        premiumButton.setFocusPainted(false);
        premiumButton.setBounds(300, 300, 200, 50);
        premiumButton.addActionListener(e -> {
            PremiumDialog premiumDialog = new PremiumDialog(this);
            if (premiumDialog.isConfirmed()) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    JFrame premiumFrame = new JFrame("Premium Chase");
                    premiumFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    premiumFrame.setLayout(new BorderLayout());
                    premiumFrame.setResizable(false);


                    PremiumGamePanel premiumGamePanel = new PremiumGamePanel();
                    premiumFrame.add(premiumGamePanel, BorderLayout.CENTER);

                    ButtonPanel buttonPanel = new ButtonPanel(premiumGamePanel, premiumFrame);
                    premiumFrame.add(buttonPanel, BorderLayout.EAST);

                    premiumFrame.pack();
                    premiumFrame.setLocationRelativeTo(null);
                    premiumFrame.setVisible(true);
                });
            }
        });

        // Adding buttons to the main panel
        mainPanel.add(startGameButton);
        mainPanel.add(premiumButton);

        // Adding main panel to the frame
        add(mainPanel);
    }

}
