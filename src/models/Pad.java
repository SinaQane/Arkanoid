package models;

import java.awt.*;
import java.util.Date;

public class Pad
{
    int x;
    int y;
    int length;
    int height;
    long activationTime;
    boolean dizzy;
    GamePanel gamePanel;

    Pad()
    {
        this.x = 350;
        this.y = 650;
        this.height = 30;
        this.length = 100;
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
        return new Rectangle(x-length/2, y-height/2, length, height);
    }

    public void activateDizzy()
    {
        this.dizzy = true;
        this.activationTime = new Date().getTime();
        // TODO Timer for deactivation
    }

    public void moveLeft()
    {
        if (dizzy)
        {
            if (x + length/10 < gamePanel.getLength())
                x = x - length/10;
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
                x = x - length/10;
            else
                x = gamePanel.getLength() - length/2;
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
}
