package models.bricks;

import models.GamePanel;
import models.prizes.Prize;

import java.awt.*;

public class Brick
{
    double x;
    double y;
    double length;
    double height;
    int lives;
    boolean intact;
    boolean visible;
    String kind;
    Prize prize;
    Color color;
    GamePanel gamePanel;

    Brick(double x, double y)
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

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)(x-length/2), (int)(y-height/2), (int)(length), (int)(height));
    }

    public void getHit()
    {
        if (this.lives > 0)
        {
            this.lives--;
            if (this.lives == 0)
            {
                this.intact = false;
                this.prize.setReleased();
            }
        }
    }

    public String getKind()
    {
        return kind;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getLength()
    {
        return length;
    }

    public double getHeight()
    {
        return height;
    }

    public void setPrize(Prize prize)
    {
        this.prize = prize;
    }

    public void setLives(int lives)
    {
        this.lives = lives;
    }
}
