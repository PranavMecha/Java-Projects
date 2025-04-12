package pingpong;

import java.awt.*;

public class Ball {
    private int x, y;
    private int diameter;
    private int speedX, speedY;
    private Color color;

    public Ball(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.speedX = 3;
        this.speedY = 3;
        this.color = Color.WHITE;
    }

    public void move() {
        x += speedX;
        y += speedY;
    }

    public void checkCollisionWithWalls(int windowHeight) {
        if (y <= 0 || y >= windowHeight - diameter) {
            speedY = -speedY;
        }
    }

    public boolean checkCollisionWithPaddle(Paddle paddle) {
        if (x <= paddle.getX() + paddle.getWidth() && 
            x + diameter >= paddle.getX() && 
            y + diameter >= paddle.getY() && 
            y <= paddle.getY() + paddle.getHeight()) {
            speedX = -speedX;
            return true;
        }
        return false;
    }

    public void reset(int windowWidth, int windowHeight) {
        x = windowWidth / 2;
        y = windowHeight / 2;
        speedX = -speedX;
        speedY = (int) (Math.random() * 3) + 1;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public boolean isOutOfBounds(int windowWidth) {
        return x <= 0 || x >= windowWidth;
    }
} 