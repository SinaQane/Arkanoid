package models.bricks;

import models.prizes.Prize;

import java.awt.*;

public class Brick
{
    int x;
    int y;
    int length;
    int width;
    int lives;
    boolean intact;
    boolean visible;
    Prize prize;
    Color color;

    Brick(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.length = 75;
        this.lives = 1;
        this.intact = true;
        this.visible = true;
        this.prize = null;
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

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }
}
