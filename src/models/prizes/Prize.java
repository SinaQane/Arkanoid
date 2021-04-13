package models.prizes;

import models.GamePanel;
import models.User;
import models.bricks.Brick;

import java.awt.*;

public class Prize
{
    Brick brick;
    double x;
    double y;
    double dy;
    double radius;
    boolean released;
    String kind;

    Prize(Brick brick)
    {
        this.brick = brick;
        this.x = brick.getX();
        this.y = brick.getY();
        this.dy = 2;
        this.radius = 5;
        this.released = false;
        brick.setPrize(this);
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setReleased()
    {
        this.released = true;
        try
        {
            this.brick.getGamePanel().addToReleasedPrizes(this);
        }
        catch (Exception e)
        {
            System.out.println("Error " + e);
        }
    }

    public void setReleased(boolean released)
    {
        this.released = released;
    }

    public boolean isReleased()
    {
        return released;
    }

    public void movePrize()
    {
        y = y + dy;
        if (y > this.brick.getGamePanel().getHeight())
            this.released = false;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)(x), (int)(y), (int)(2*radius), (int)(2*radius));
    }

    public void usePrize(User user)
    {
        System.out.println("Prize used.");
    }

    public String getKind()
    {
        return kind;
    }
}
