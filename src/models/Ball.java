package models;

import graphics.GameFrame;
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
        this.y = 545;
        this.x = 600;
        this.radius = 10;
        this.dx = 2;
        this.dy = 2;
        this.fireBall = false;
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

                        else if (y + 5 >= brick.getY() + brick.getHeight())
                            dy = Math.abs(dy);

                        else if (x + 2*radius - 5 <= brick.getX() )
                            dx = -Math.abs(dx);

                        else if (x + 5 >= brick.getX() + brick.getLength())
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

    public void moveBall()
    {
        this.checkIntersections();
        x = x + dx;
        y = y + dy;
    }
}
