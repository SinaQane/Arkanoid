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
    boolean inSight;
    boolean visible;
    String kind;
    Prize prize;
    GamePanel gamePanel;

    Brick(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.height = 50;
        this.length = 100;
        this.lives = 1;
        this.intact = true;
        this.inSight = true;
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
        return new Rectangle((int)(x), (int)(y), (int)(length), (int)(height));
    }

    public void blink()
    {
        System.out.println("Beep");
    }

    public void getHit()
    {
        this.getGamePanel().getUser().setScore(this.getGamePanel().getUser().getScore() + 1);
        if (this.lives > 0)
        {
            this.lives--;
            if (this.lives == 0)
            {
                this.intact = false;
                if(this.prize != null)
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
        if (lives == 0)
            this.intact = false;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public int getLives()
    {
        return lives;
    }

    public boolean getInSight()
    {
        return inSight;
    }

    public Prize getPrize()
    {
        return prize;
    }

    public boolean getIntact()
    {
        return this.intact;
    }
}
