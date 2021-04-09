package models;

import models.bricks.Brick;

import java.awt.*;
import java.util.Date;

public class Ball
{
    double x;
    double y;
    double radius;
    double dx;
    double dy;
    boolean fireBall;
    long activationTime;
    GamePanel gamePanel;

    public Ball()
    {
        this.y = 600; // gamePanel will be 700 * 700
        this.x = 350;
        this.radius = 10;
        this.dx = 0;
        this.dy = 5;
        this.fireBall = false;
        this.activationTime = 0;
    }

    public void activateFireBall()
    {
        this.fireBall = true;
        this.activationTime = new Date().getTime();
    }

    public void deactivateFireBall() // TODO Use this in graphics
    {
        if (new Date().getTime() - this.getActivationTime() < 30000)
            return;
        this.fireBall = false;
        this.activationTime = 0;
    }

    public void faster()
    {
        this.dx = 1.25 * dx;
        this.dy = 1.25 * dy;
    }

    public void slower()
    {
        this.dx = 0.8 * dx;
        this.dy = 0.8 * dy;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setDx(double dx)
    {
        this.dx = dx;
    }

    public void setDy(double dy)
    {
        this.dy = dy;
    }

    public void setFireBall(boolean fireBall)
    {
        this.fireBall = fireBall;
    }

    public void setActivationTime(long activationTime)
    {
        this.activationTime = activationTime;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public boolean getFireBall()
    {
        return fireBall;
    }

    public long getActivationTime()
    {
        return activationTime;
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)(x-radius), (int)(y-radius), (int)(2*radius), (int)(2*radius));
    }

    public boolean brickCollision(Brick brick)
    {
        return brick.getBounds().intersects(this.getBounds());
    }

    public boolean padCollision()
    {
        return gamePanel.getPad().getBounds().intersects(this.getBounds());
    }

    public void checkIntersections()
    {
        // Panel intersections
        if (x + dx + radius < 0)
            dx = -dx;
        else if (x + dx + radius> gamePanel.getLength())
            dx = -dx;
        else if (y + dy + radius < 0)
            dy = -dy;
        else if (y + dy + radius > gamePanel.getHeight())
        {
            if (gamePanel.getBalls().size()==1)
                gamePanel.getUser().loseLife();
            gamePanel.getBalls().remove(this);
        }

        // Brick intersections
        for (Brick brick : gamePanel.getBricks())
        {
            if (brick.getIntact())
            {
                if (this.brickCollision(brick))
                {
                    if (!this.fireBall && brick.getInSight())
                    {
                        if (y + radius - Math.abs(dy) - 1 <= brick.getY() - brick.getHeight() / 2)
                            dy = -Math.abs(dy);
                        else if (y + radius + Math.abs(dy) + 1 >= brick.getY() + brick.getHeight() / 2)
                            dy = Math.abs(dy);
                        else if (x + radius - Math.abs(dx) - 1 <= brick.getX() - brick.getLength() / 2)
                            dx = -Math.abs(dx);
                        else if (x + radius + Math.abs(dx) + 1 >= brick.getX() + brick.getLength() / 2)
                            dx = Math.abs(dx);
                    }
                    brick.getHit();
                }
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
        this.checkIntersections();
        x = x + dx;
        y = y + dy;
    }
}
