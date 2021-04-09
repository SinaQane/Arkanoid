package models;

import models.bricks.Brick;

import java.util.List;

public class GamePanel
{
    String name;
    int height;
    int length;
    Pad pad;
    User user;
    List<Ball> balls;
    List<Brick> bricks;

    public GamePanel(String name, User user, List<Brick> bricks, List<Ball> balls, Pad pad)
    {
        this.name = name;
        this.height = 700;
        this.length = 700;
        this.pad = pad;
        this.balls = balls;
        this.user = user;
        this.bricks = bricks;
        pad.setGamePanel(this);
        user.setGamePanel(this);
        for (Ball ball: balls)
        {
            ball.setGamePanel(this);
        }
        for (Brick brick : bricks)
        {
            brick.setGamePanel(this);
        }
    }

    public String getName()
    {
        return name;
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

    public List<Ball> getBalls() {
        return this.balls;
    }

    public List<Brick> getBricks()
    {
        return this.bricks;
    }
}
