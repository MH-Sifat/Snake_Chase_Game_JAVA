package snakegame.design;

import snakegame.logic.GamePanel;
import snakegame.logic.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class PremiumGamePanel extends GamePanel {
    private int lives;
    private Image backgroundImage;
    private Image lifeImage;


    public PremiumGamePanel() {
        super();
        lives = 3; // default number of lives
        backgroundImage = new ImageIcon("Images/playground.jpg").getImage();
        lifeImage = new ImageIcon("Images/life.png").getImage();

    }


    public void startGame() {
        if (timer != null) {
            timer.stop();
        }
        if (bonusTimer != null) {
            bonusTimer.stop();
        }

        super.startGame(); //  Reseting snake, food and scores
        lives = 3;         // Reseting the lives count for the premium game
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the background image to fill entire panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);


        if (running) {
            // Drawing the snake, food and other game things
            snake.draw(g);
            food.draw(g);
            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, 10, 10);
            g.drawString("High Score: " + highScore, 10, 25);

            // Drawing bonus food as an image if it is active
            if (bonusFoodActive && bonusFood != null) {
                Image bonusFoodImage = new ImageIcon("Images/bonus.png").getImage();
                g.drawImage(bonusFoodImage, bonusFood.getX(), bonusFood.getY(), bonusFood.getSize(), bonusFood.getSize(), null);
            }

            // Drawing lives as images at the center
            for (int i = 0; i < lives; i++) {
                // Adjusting the spacing  between each life icon
                g.drawImage(lifeImage, (getWidth() / 2) - (30 * (lives - i)) + 10, 10, 20, 20, null);
            }

        } else {
            // Drawing the game over screen
            super.paintComponent(g);
        }
    }



    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            snake.move();
            if (snake.checkCollision() || snake.isOutOfBounds(width, height)) {
                lives--;
                if (lives > 0) {
                    snake = new Snake(unit_size, unit_size, unit_size);
                } else {
                    running = false;
                }
            }

            // Checking collision with food
            if (snake.getBody().get(0).x == food.getX() && snake.getBody().get(0).y == food.getY()) {
                snake.grow();
                score++;
                food.setPosition(random.nextInt(width / unit_size) * unit_size,
                        random.nextInt(height / unit_size) * unit_size);
            }

            // Checking collision with bonus food
            if (bonusFoodActive && bonusFood != null &&
                    snake.getBody().get(0).x == bonusFood.getX() &&
                    snake.getBody().get(0).y == bonusFood.getY()) {
                score += 5;
                bonusFood = null;
                bonusFoodActive = false;
            }

            repaint();
        }
    }
}
