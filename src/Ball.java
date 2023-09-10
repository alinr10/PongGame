import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Ball extends Rectangle {

    Random random;
    int xVelocity;
    int yVelocity;
    int xdir;
    int ydir;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        xdir = random.nextInt(2);
        if (xdir == 0) {
            xdir -= 2;
            setXDirection(xdir);

        } else {
            xdir += 1;
            setXDirection(xdir);
        }
        ydir = random.nextInt(2);
        if (ydir == 0) {
            ydir -= 2;
            setYDirection(ydir);

        } else {
            ydir += 1;
            setYDirection(ydir);
        }

    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;

    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;

    }

    public void move() {
        y += yVelocity;
        x += xVelocity;
        if (y <= 0) {
            yVelocity *= -1;

        }
        if (y >= GamePanel.GAME_HEIGHT - GamePanel.BALL_DIAMETER) {
            yVelocity *= -1;
        }

    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);

    }

}
