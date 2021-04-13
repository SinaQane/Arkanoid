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
    boolean isAvailable;
    long activationTime;
    GamePanel gamePanel;

    public Ball()
    {
        this.y = 545;
        this.x = 600;
        this.radius = 10;
        this.dx = 1;
        this.dy = 1;
        this.fireBall = false;
        this.isAvailable = true;
        this.activationTime = 0;
    }

    public void activateFireBall()
    {
        this.fireBall = true;
        this.activationTime = new Date().getTime();
    }

    public void deactivateFireBall()
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

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getDx()
    {
        return dx;
    }

    public double getDy()
    {
        return dy;
    }

    public double getRadius()
    {
        return radius;
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
        return new Rectangle((int)(x), (int)(y), (int)(2*radius), (int)(2*radius));
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
        if (x <= 0)
            dx = -dx;
        else if (x + radius>= gamePanel.getLength())
            dx = -dx;
        else if (y <= 0)
            dy = -dy;

        // Brick intersections
        for (Brick brick : gamePanel.getBricks())
        {
            if (brick.getIntact())
            {
                if (this.brickCollision(brick))
                {
                    if (!this.fireBall && brick.getInSight())
                    {
                        if (y - 2*radius - 5 <= brick.getY())
                            dy = -Math.abs(dy);
                        if (y + 5 >= brick.getY() + brick.getHeight())
                            dy = Math.abs(dy);
                        if (x + 2*radius - 5 <= brick.getX())
                            dx = -Math.abs(dx);
                        if (x + 5 >= brick.getX() + brick.getLength())
                            dx = Math.abs(dx);
                    }
                    if (brick.getInSight())
                    {
                        this.getGamePanel().getUser().setScore(this.getGamePanel().getUser().getScore() + 1);
                        brick.getHit();
                    }
                }
            }
        }

        // Pad intersections
        if (this.padCollision())
        {
            double difference = Math.abs(this.x - gamePanel.getPad().getX());
            double ratio = difference / (gamePanel.getPad().getLength() / 2);
            dx = ratio * dx;
            dy = -Math.abs(dy) + ratio * (Math.abs(dy) - Math.sqrt(dy*dy + dx*dx));
        }
    }

    public void moveBall()
    {
        if (this.isAvailable)
        {
            this.checkIntersections();
            x = x + dx;
            y = y + dy;
        }
    }

    public boolean isAvailable()
    {
        return isAvailable;
    }

    public void setAvailable(boolean available)
    {
        isAvailable = available;
    }
}
