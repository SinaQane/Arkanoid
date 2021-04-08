package models.prizes;

import models.User;
import models.bricks.Brick;

import java.awt.*;

public class Prize
{
    Brick brick;
    int x;
    int y;
    int dy;
    int radius;
    boolean released;

    Prize(Brick brick)
    {
        this.brick = brick;
        this.x = brick.getX();
        this.y = brick.getY();
        this.dy = 5;
        this.radius = 10;
        this.released = false;
        brick.setPrize(this);
    }

    public void setReleased()
    {
        this.released = true;
        // TODO move();
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x-radius, y-radius, 2*radius, 2*radius);
    }

    public void usePrize(User user)
    {
        System.out.println("Use Prize");
    }
}
