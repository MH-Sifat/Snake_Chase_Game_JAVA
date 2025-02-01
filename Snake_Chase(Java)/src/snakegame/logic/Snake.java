package snakegame.logic;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends GameEntity {
    private final ArrayList<Point> body;
    private char direction;

    public Snake(int x, int y, int size) {
        super(x, y, size);
        body = new ArrayList<>();
        body.add(new Point(x, y));
        direction = 'R'; // default direction
    }

    public void move() {
        // Updating body positions
        for (int i = body.size() - 1; i > 0; i--) {
            body.set(i, new Point(body.get(i - 1)));
        }

        // Moving head
        Point head = body.get(0);
        switch (direction) {
            case 'U' -> head.y -= size;
            case 'D' -> head.y += size;
            case 'L' -> head.x -= size;
            case 'R' -> head.x += size;
        }
    }

    public void grow() {
        body.add(new Point(body.get(body.size() - 1)));
    }

    public boolean checkCollision() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean isOutOfBounds(int width, int height) {
        Point head = body.get(0);
        return head.x < 0 || head.x >= width || head.y < 0 || head.y >= height;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < body.size(); i++) {
            g.setColor(i == 0 ? Color.BLUE : Color.LIGHT_GRAY);
            g.fillRect(body.get(i).x, body.get(i).y, size, size);
        }
    }
}
