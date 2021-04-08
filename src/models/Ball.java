package models;

import graphics.GamePanel;
import models.bricks.Brick;

import java.awt.*;
import java.util.Date;

public class Ball
{
    int x;
    int y;
    int radius;
    int dx;
    int dy;
    boolean fireBall;
    long activationTime;
    GamePanel gamePanel;

    Ball()
    {
        this.y = 600; // gamePanel will be 700 * 700
        this.x = 350;
        this.radius = 10;
        this.dx = 0;
        this.dy = 1;
        this.fireBall = false;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x-radius, y-radius, 2*radius, 2*radius);
    }

    public boolean brickCollision(Brick brick)
    {
        return brick.getBounds().intersects(getBounds());
    }

    public boolean padCollision()
    {
        return gamePanel.getPad().getBounds().intersects(getBounds());
    }

    public void activateFireBall()
    {
        this.fireBall = true;
        this.activationTime = new Date().getTime();
        // TODO Timer for deactivation
    }

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public void intersections()
    {
        // Panel intersections
        if (x + dx + radius < 0)
            dx = -dx;
        else if (x + dx + radius> gamePanel.getLength())
            dx = -dx;
        else if (y + dy + radius < 0)
            dy = -dy;
        else if (y + dy + radius > gamePanel.getHeight())
            gamePanel.getUser().loseLife();

        // Brick intersections
        for (Brick brick : gamePanel.getBricks())
        {
            if (this.brickCollision(brick))
            {
                if (y + radius - Math.abs(dy) - 1 <= brick.getY() - brick.getHeight()/2)
                    dy = -Math.abs(dy);
                else if (y + radius + Math.abs(dy) + 1 >= brick.getY() + brick.getHeight()/2)
                    dy = Math.abs(dy);
                else if (x + radius - Math.abs(dx) - 1 <= brick.getX() - brick.getLength()/2)
                    dx = -Math.abs(dx);
                else if (x + radius + Math.abs(dx) + 1 >= brick.getX() + brick.getLength()/2)
                    dx = Math.abs(dx);
                brick.getHit();
            }
        }

        // Pad intersections
        if (this.padCollision())
        {
            // TODO make this more accurate
            dy = -dy;
        }
    }

    public void move()
    {
        x = x + dx;
        y = y + dy;
    }


}
