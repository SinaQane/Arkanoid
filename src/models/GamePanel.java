package models;

import models.bricks.*;
import models.prizes.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GamePanel
{
    String name;
    int height;
    int length;
    Pad pad;
    User user;
    List<Ball> balls;
    List<Brick> bricks;
    List<Prize> releasedPrizes;
    long rowAddedTime;

    public GamePanel(String name, User user, List<Brick> bricks, List<Ball> balls, Pad pad)
    {
        this.name = name;
        this.height = 800;
        this.length = 1000;
        this.pad = pad;
        this.balls = balls;
        this.user = user;
        this.bricks = bricks;
        this.rowAddedTime = new Date().getTime();
        this.releasedPrizes = new LinkedList<>();
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

    public List<Prize> getReleasedPrizes()
    {
        return this.releasedPrizes;
    }

    public void addRow()
    {
        if (new Date().getTime() - rowAddedTime > 10000)
        {
            for (Brick brick : bricks)
            {
                brick.setY(brick.getY() + 50);
            }
            Random random = new Random();
            for (int i = 0; i < 10; i++)
            {
                int brickKind = random.nextInt(4);
                int prizeKind = random.nextInt(9);

                Brick brick = null;

                switch (brickKind)
                {
                    case 0:
                        brick = new GlassBrick(50 + i*100, 25);
                        break;
                    case 1:
                        brick = new WoodenBrick(50 + i*100, 25);
                        break;
                    case 2:
                        brick = new InvisibleBrick(50 + i*100, 25);
                        break;
                    case 3:
                        brick = new BlinkingBrick(50 + i*100, 25);
                        break;
                }

                Prize prize;

                switch (prizeKind)
                {
                    case 0:
                        prize = new BiggerPadPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 1:
                        prize = new SmallerPadPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 2:
                        prize = new FasterBallPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 3:
                        prize = new SlowerBallPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 4:
                        prize = new FireBallPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 5:
                        prize = new TripleBallPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 6:
                        prize = new DizzyPadPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 7:
                        prize = new RandomPrize(brick);
                        brick.setPrize(prize);
                        break;
                    case 8:
                        brick.setPrize(null);
                        break;
                }

                bricks.add(brick);
            }
        }
    }
}
