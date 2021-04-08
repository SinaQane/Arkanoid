package models;

public class User
{
    int lives;

    User()
    {
        this.lives = 3;
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
