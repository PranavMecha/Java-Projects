package pingpong;

import java.awt.*;

public class Paddle {
    private int x, y;
    private int width, height;
    private int speed;
    private Color color;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 5;
        this.color = Color.WHITE;
    }

    public void moveUp() {
        y = Math.max(0, y - speed);
    }

    public void moveDown(int windowHeight) {
        y = Math.min(windowHeight - height, y + speed);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
} 