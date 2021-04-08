package models;

import graphics.GamePanel;

import java.awt.*;

public class Pad
{
    int x;
    int y;
    int length;
    int height;
    GamePanel gamePanel;

    Pad(GamePanel gamePanel)
    {
        this.x = 350;
        this.y = 650;
        this.height = 30;
        this.length = 100;
        this.gamePanel = gamePanel;
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x-length/2, y-height/2, length, height);
    }

    public void moveLeft()
    {
        if (x - length/10 > 0)
            x = x - length/10;
        else
            x = length/2;
    }

    public void moveRight()
    {
        if (x + length/10 < gamePanel.getLength())
            x = x - length/10;
        else
            x = gamePanel.getLength() - length/2;
    }
}
