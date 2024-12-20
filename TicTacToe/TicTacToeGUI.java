/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #1
 * 1 - 5026231030 - Jonathan Abimanyu Trisno
 * 2 - 5026231032 - Yokanan Prawira Nugroho
 * 3 - 5026231133 - Muhammad Rifqi Alfareza Santosa
 */

package TicTacToe;

import java.awt.*;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.*;

public class TicTacToeGUI extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public TicTacToeGUI() {
        setTitle("Welcome to Tic Tac Toe");
        setSize(380, 415);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel menuPanel = createMenuPanel();
        GameMain gamePanelHumanVsAI = new GameMain(false, cardLayout, mainPanel);  // Pass references
        GameMain gamePanelHumanVsHuman = new GameMain(true, cardLayout, mainPanel); // Pass references

        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(gamePanelHumanVsAI, "HumanVsAI");
        mainPanel.add(gamePanelHumanVsHuman, "HumanVsHuman");

        add(mainPanel);
        cardLayout.show(mainPanel, "Menu");

        playSound("image and background/Undertale OST_ 023 - Shop.wav");
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Set background image
                ImageIcon bgImage = new ImageIcon("image and background/7128.gif");
                g.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());

        JLabel titleLabel = new JLabel("Welcome to Tic Tac Toe");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.white);

        // Create rounded buttons
        JButton humanVsAIButton = createRoundedButton("Human vs AI");
        JButton humanVsHumanButton = createRoundedButton("Human vs Human");

        humanVsAIButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "HumanVsAI");
            playSound("image and background/ok let's go sound effect.wav");
        });

        humanVsHumanButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "HumanVsHuman");
            playSound("image and background/Get Ready - Sound Effect.wav");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 20, 20));
        buttonPanel.add(humanVsAIButton);
        buttonPanel.add(humanVsHumanButton);

        panel.add(titleLabel, new GridBagConstraints());
        panel.add(buttonPanel, new GridBagConstraints());

        return panel;
    }

    private JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw rounded background
                g2d.setColor(new Color(64, 154, 225)); // Button background color
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // Draw button text
                g2d.setColor(Color.WHITE);
                g2d.setFont(getFont());
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
                g2d.drawString(getText(), x, y);

                g2d.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                // No border painting to avoid default rectangle border
            }
        };

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    private void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToeGUI().setVisible(true));
    }
}
