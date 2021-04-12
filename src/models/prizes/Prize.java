package models.prizes;

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
        this.dy = 5;
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
        this.brick.getGamePanel().getReleasedPrizes().add(this);
    }

    public void movePrize()
    {
        y = y + dy;
        if (y > this.brick.getGamePanel().getHeight())
            this.brick.getGamePanel().getReleasedPrizes().remove(this);
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)(x), (int)(y), (int)(2*radius), (int)(2*radius));
    }

    public void usePrize(User user)
    {
        System.out.println("Use Prize");
    }

    public String getKind()
    {
        return kind;
    }
}
