package graphics;

import models.Pad;
import models.User;
import models.bricks.Brick;

import java.util.List;

public class GamePanel
{
    User user;
    int height;
    int length;
    Pad pad;
    List<Brick> bricks;

    GamePanel(User user, List<Brick> bricks, Pad pad)
    {
        this.user = user;
        this.height = 700;
        this.length = 700;
        this.bricks = bricks;
        this.pad = pad;
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

    public List<Brick> getBricks()
    {
        return this.bricks;
    }
}
