package models;

public class User
{
    int lives;
    int score;
    GamePanel gamePanel;

    public User()
    {
        this.lives = 3;
        this.score = 0;
    }

    public void setLives(int lives)
    {
        this.lives = lives;
    }

    public void setScore(int score)
    {
        this.score = score;
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
