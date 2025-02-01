package snakegame.design;

import javax.swing.*;
import java.awt.*;

public class PremiumDialog extends JDialog {
    private boolean confirmed = false;

    public PremiumDialog(JFrame parent) {
        super(parent, "Premium Membership", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);


        // Title label
        JLabel titleLabel = new JLabel("Buy Premium Membership For 1$", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Center form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Form fields
        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone Number:"));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Card Number:"));
        JTextField cardNumberField = new JTextField();
        formPanel.add(cardNumberField);
        formPanel.add(new JLabel("Pin:"));
        JPasswordField pinField = new JPasswordField();
        formPanel.add(pinField);

        // Bottom panel for the Buy button
        JPanel buttonPanel = new JPanel();
        JButton buyButton = new JButton("Buy Membership");
        buyButton.setBackground(Color.BLACK);
        buyButton.setForeground(Color.WHITE);
        buyButton.setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 14));
        Dimension buttonSize = new Dimension(150, 40);
        buyButton.setPreferredSize(buttonSize);
        buttonPanel.add(buyButton);

        // Adding action listener for the buy button
        buyButton.addActionListener(e -> {
            // Validate checking inputs
            if (nameField.getText().trim().isEmpty() ||
                    phoneField.getText().trim().isEmpty() ||
                    cardNumberField.getText().trim().isEmpty() ||
                    new String(pinField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Confirm purchase
            int choice = JOptionPane.showConfirmDialog(this, "Confirm $1 purchase?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                confirmed = true;
                dispose();
            }
        });

        // Add to the dialog
        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        setVisible(true);
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
