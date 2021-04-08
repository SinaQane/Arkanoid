package models.bricks;

import models.prizes.Prize;

import java.awt.*;

public class Brick
{
    int x;
    int y;
    int length;
    int height;
    int lives;
    boolean intact;
    boolean visible;
    Prize prize;
    Color color;

    Brick(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.height = 15;
        this.length = 30;
        this.lives = 1;
        this.intact = true;
        this.visible = true;
        this.prize = null;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x-length/2, y-height/2, length, height);
    }

    public void getHit()
    {
        if (this.lives > 0)
        {
            this.lives--;
            if (this.lives == 0)
            {
                this.intact = false;
                // TODO losePrize
            }
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getLength()
    {
        return length;
    }

    public int getHeight()
    {
        return height;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }
}
