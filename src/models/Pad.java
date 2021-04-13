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
        this.y = 565;
        this.height = 10;
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
        return new Rectangle((int)(x), (int)(y), (int)length, (int)height);
    }

    public void activateDizzy()
    {
        this.dizzy = true;
        this.activationTime = new Date().getTime();
    }

    public void deactivateDizzy()
    {
        if (new Date().getTime() - this.getActivationTime() < 5000)
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
            if (x + length + length/10 <= gamePanel.getLength())
                x = x + length/10;
            else
                x = gamePanel.getLength() - length;
        }
        else
        {
            if (x - length / 10 >= 0)
                x = x - length / 10;
            else
                x = 0;
        }
    }

    public void moveRight()
    {
        if (dizzy)
        {
            if (x - length / 10 >= 0)
                x = x - length / 10;
            else
                x = 0;
        }
        else
        {
            if (x + length + length/10 <= gamePanel.getLength())
                x = x + length/10;
            else
                x = gamePanel.getLength() - length;
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

    public double getHeight()
    {
        return height;
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