package models;

import models.prizes.Prize;

import java.awt.*;
import java.util.Date;

public class Pad
{
    double x;
    double y;
    double length;
    double height;
    long activationTime;
    boolean dizzy;
    GamePanel gamePanel;

    public Pad()
    {
        this.x = 500;
        this.y = 785;
        this.height = 30;
        this.length = 200;
        this.dizzy = false;
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
        return new Rectangle((int)(x-length/2), (int)(y-height/2), (int)length, (int)height);
    }

    public void activateDizzy()
    {
        this.dizzy = true;
        this.activationTime = new Date().getTime();
    }

    public void deactivateDizzy() // TODO Use this in graphics
    {
        if (new Date().getTime() - this.getActivationTime() < 30000)
            return;
        this.dizzy = false;
        this.activationTime = 0;
    }

    public boolean prizeCollision(Prize prize)
    {
        return this.getBounds().intersects(prize.getBounds());
    }

    public void moveLeft()
    {
        if (dizzy)
        {
            if (x + length/10 < gamePanel.getLength())
                x = x + length/10;
            else
                x = gamePanel.getLength() - length/2;
        }
        else
        {
            if (x - length / 10 > 0)
                x = x - length / 10;
            else
                x = length / 2;
        }
        for (Prize prize : this.getGamePanel().getReleasedPrizes())
        {
            if (this.prizeCollision(prize))
            {
                prize.usePrize(this.getGamePanel().getUser());
                this.getGamePanel().getReleasedPrizes().remove(prize);
            }
        }
    }

    public void moveRight()
    {
        if (dizzy)
        {
            if (x - length / 10 > 0)
                x = x - length / 10;
            else
                x = length / 2;
        }
        else
        {
            if (x + length/10 < gamePanel.getLength())
                x = x + length/10;
            else
                x = gamePanel.getLength() - length/2;
        }
        for (Prize prize : this.getGamePanel().getReleasedPrizes())
        {
            if (this.prizeCollision(prize))
            {
                prize.usePrize(this.getGamePanel().getUser());
                this.getGamePanel().getReleasedPrizes().remove(prize);
            }
        }
    }

    public void bigger()
    {
        if (length + 25 <= 150)
            length += 25;
        else
            length = 150;
    }

    public void smaller()
    {
        if (length - 25 >= 50)
            length -= 25;
        else
            length = 50;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public void setLength(double length)
    {
        this.length = length;
    }

    public void setActivationTime(long activationTime)
    {
        this.activationTime = activationTime;
    }

    public void setDizzy(boolean dizzy)
    {
        this.dizzy = dizzy;
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

    public long getActivationTime()
    {
        return activationTime;
    }

    public boolean getDizzy()
    {
        return dizzy;
    }
}
