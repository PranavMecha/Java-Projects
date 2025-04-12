package pingpong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 20;
    private static final int PADDLE_HEIGHT = 100;
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_OFFSET = 50;

    private Ball ball;
    private Paddle player1;
    private Paddle player2;
    private Timer timer;
    private int player1Score;
    private int player2Score;

    public GamePanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Initialize game objects
        ball = new Ball(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, BALL_DIAMETER);
        player1 = new Paddle(PADDLE_OFFSET, WINDOW_HEIGHT / 2 - PADDLE_HEIGHT / 2, PADDLE_WIDTH, PADDLE_HEIGHT);
        player2 = new Paddle(WINDOW_WIDTH - PADDLE_OFFSET - PADDLE_WIDTH, 
                           WINDOW_HEIGHT / 2 - PADDLE_HEIGHT / 2, 
                           PADDLE_WIDTH, PADDLE_HEIGHT);

        // Set up keyboard controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        player1.moveUp();
                        break;
                    case KeyEvent.VK_S:
                        player1.moveDown(WINDOW_HEIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        player2.moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        player2.moveDown(WINDOW_HEIGHT);
                        break;
                }
            }
        });

        // Set up game timer
        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw game objects
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);

        // Draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(String.valueOf(player1Score), WINDOW_WIDTH / 4, 50);
        g.drawString(String.valueOf(player2Score), 3 * WINDOW_WIDTH / 4, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state
        ball.move();
        ball.checkCollisionWithWalls(WINDOW_HEIGHT);
        
        if (ball.checkCollisionWithPaddle(player1) || ball.checkCollisionWithPaddle(player2)) {
            // Ball hit a paddle
        }

        if (ball.isOutOfBounds(WINDOW_WIDTH)) {
            if (ball.getX() <= 0) {
                player2Score++;
            } else {
                player1Score++;
            }
            ball.reset(WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        repaint();
    }
} 