import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable {

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * 0.55555);

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    static final int BALL_DIAMETER = 20;

    static final int PADDLE_WIDTH = 25;

    static final int PADDLE_HEIGHT = 100;

    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
    Paddle paddle3;
    Ball ball;
    Score score;

    public GamePanel() {
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        ball = new Ball(GAME_WIDTH / 2 - BALL_DIAMETER / 2, GAME_HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1, ball);
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2, ball);
        //paddle3 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 3);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);

    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.y = ball.y;
        paddle2.move();
        ball.move();

    }

    public void checkCollision() {
        if (paddle1.intersects(ball)) {
            ball.xVelocity *= -1;
            if (ball.xVelocity > 0) {
                ball.xVelocity++;
            } else ball.xVelocity--;

        }
        if (paddle2.intersects(ball)) {
            ball.xVelocity *= -1;
            if (ball.xVelocity > 0) {
                ball.xVelocity++;
            } else ball.xVelocity--;

        }

    }

    public void score() {
        if (ball.x < 0) {
            score.player2++;
            newBall();
            ball.draw(graphics);

        }
        if (ball.x >= GAME_WIDTH) {
            score.player1++;
            newBall();
            ball.draw(graphics);

        }

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                score();
                repaint();
                delta--;
            }
        }

    }

    public class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }

    }

}
