package snakegame.logic;

import java.awt.*;

public class Food extends GameEntity {
    public Food(int x, int y, int size) {
        super(x, y, size);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }
}
