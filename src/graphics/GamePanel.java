package graphics;

import models.Ball;
import models.Pad;
import models.User;
import models.bricks.Brick;

import java.util.List;

public class GamePanel
{
    int height;
    int length;
    Pad pad;
    Ball ball;
    User user;
    List<Brick> bricks;

    GamePanel(User user, List<Brick> bricks, Ball ball, Pad pad)
    {
        this.height = 700;
        this.length = 700;
        this.pad = pad;
        this.ball = ball;
        this.user = user;
        this.bricks = bricks;
        pad.setGamePanel(this);
        user.setGamePanel(this);
        ball.setGamePanel(this);
        for (Brick brick : bricks)
        {
            brick.setGamePanel(this);
        }
    }

    public User getUser()
    {
        return this.user;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getLength()
    {
        return this.length;
    }

    public Pad getPad()
    {
        return this.pad;
    }

    public Ball getBall() {
        return ball;
    }

    public List<Brick> getBricks()
    {
        return this.bricks;
    }
}
