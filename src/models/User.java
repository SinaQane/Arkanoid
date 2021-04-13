package models;

public class User
{
    String name;
    int lives;
    int score;
    GamePanel gamePanel;

    public User(String name)
    {
        this.name = name;
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
        this.setScore(this.getScore() - 3);
        this.lives--;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getLives()
    {
        return lives;
    }

    public int getScore()
    {
        return score;
    }
}