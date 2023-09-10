import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Paddle extends Rectangle {

    int id;
    int yVelocity;

    int speed = 10;

    Ball ball;

    public Paddle(int x, int y, int paddle_width, int paddle_height, int id, Ball ball) {
        super(x, y, paddle_width, paddle_height);
        this.id = id;
        this.ball = ball;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
                break;
                /*
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
                break;
*/
        }

    }

    public void keyReleased(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
                break;

        }

    }

    public void setYDirection(int yDirection) {
        this.yVelocity = yDirection;

    }

    public void move() {
        y += yVelocity;
        if (y <= 0) y = 0;
        if (y >= GamePanel.GAME_HEIGHT - height) y = GamePanel.GAME_HEIGHT - height;

    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);

        } else g.setColor(Color.red);
        g.fillRect(x, y, width, height);

    }

}
