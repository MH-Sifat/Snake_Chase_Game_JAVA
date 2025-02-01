package snakegame.logic;

import snakegame.management.ScoreManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    public final int width = 800, height = 600, unit_size = 25;
    public Snake snake;
    public Food food;  // normal food
    public Food bonusFood; //  bonus food
    public boolean running, paused, bonusFoodActive;
    public int score, highScore;
    public Timer timer, bonusTimer; // Bonus food timer
    public Random random;

    public GamePanel() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        random = new Random();
        startGame();
    }

    public void startGame() {
        snake = new Snake(unit_size, unit_size, unit_size);
        food = new Food(random.nextInt(width / unit_size) * unit_size,
                random.nextInt(height / unit_size) * unit_size, unit_size);
        bonusFood = null; // No bonus food
        running = true;
        paused = false;
        bonusFoodActive = false;
        score = 0;
        highScore = ScoreManager.loadHighScore();
        timer = new Timer(100, this);
        bonusTimer = new Timer(5000, e -> spawnBonusFood()); // calling method after every 5 sec to show Bonus food
        timer.start();
        bonusTimer.start();
        requestFocusInWindow();
    }

    private void spawnBonusFood() {
        if (score > 0 && score % 10 == 0 && !bonusFoodActive) {
            bonusFood = new Food(random.nextInt(width / unit_size) * unit_size,
                    random.nextInt(height / unit_size) * unit_size, unit_size);
            bonusFoodActive = true;

            //  bonus food will Remove after 10 seconds if not eaten
            Timer removeBonusTimer = new Timer(10000, e -> {
                bonusFood = null;
                bonusFoodActive = false;
                repaint();
            });
            removeBonusTimer.setRepeats(false);
            removeBonusTimer.start();
        }
    }

    public void pauseGame() {
        if (running && !paused) {
            paused = true;
            timer.stop();
            bonusTimer.stop();
        }
    }

    public void resumeGame() {
        if (running && paused) {
            paused = false;
            timer.start();
            bonusTimer.start();
            requestFocusInWindow();
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            food.draw(g);
            if (bonusFoodActive && bonusFood != null) {
                g.setColor(Color.YELLOW); // Bonus food color
                g.fillOval(bonusFood.getX(), bonusFood.getY(), bonusFood.size, bonusFood.size);
            }
            snake.draw(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 13));
            g.drawString("Score: " + score, 10, 10);
            g.drawString("High Score: " + highScore, 10, 25);
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", width / 2 - 100, 300);

        // Check if current score exceeds high score
        if (score > highScore) {
            highScore = score;
            ScoreManager.saveHighScore(highScore);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score:" + score, width / 2 - 20, 250 );
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("High Score:" + highScore, width / 2 - 50, 350);

    }


    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            snake.move();
            if (snake.checkCollision() || snake.isOutOfBounds(width, height)) {
                running = false;
                timer.stop();
                bonusTimer.stop();
            }

            // checking collision with normal food
            if (snake.getBody().get(0).x == food.getX() && snake.getBody().get(0).y == food.getY()) {
                snake.grow();
                score++;
                food.setPosition(random.nextInt(width / unit_size) * unit_size,
                        random.nextInt(height / unit_size) * unit_size);

                if (score > highScore) {
                    highScore = score;
                    ScoreManager.saveHighScore(highScore);
                }

            }

            // checking collision with bonus food
            if (bonusFoodActive && bonusFood != null &&
                    snake.getBody().get(0).x == bonusFood.getX() && snake.getBody().get(0).y == bonusFood.getY()) {
                score += 5; // Bonus food add 5 points
                bonusFood = null; // Removing bonus food
                bonusFoodActive = false;
            }
        }
        repaint();
    }


    public void keyPressed(KeyEvent e) {
        if (!paused) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> snake.setDirection('U');
                case KeyEvent.VK_DOWN -> snake.setDirection('D');
                case KeyEvent.VK_LEFT -> snake.setDirection('L');
                case KeyEvent.VK_RIGHT -> snake.setDirection('R');
            }
        }
    }


    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}
