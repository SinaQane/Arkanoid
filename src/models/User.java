package models;

import graphics.GamePanel;

public class User
{
    int lives;
    GamePanel gamePanel;

    User()
    {
        this.lives = 3;
    }

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public GamePanel getGamePanel()
    {
        return gamePanel;
    }

    public void loseLife()
    {
        this.lives--;
        /*
        TODO add losing
        if (this.lives == 0)
        {
            lose();
        }*/
    }
}
