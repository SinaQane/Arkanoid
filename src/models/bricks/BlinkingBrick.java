package models.bricks;

import java.util.Date;

public class BlinkingBrick extends Brick
{
    long lastChangeMoodTime;
    public BlinkingBrick(double x, double y)
    {
        super(x, y);
        this.lastChangeMoodTime = 0;
        this.kind = "BLINKING";
    }

    public void blink() // TODO Use this in graphics
    {
        if (new Date().getTime() - lastChangeMoodTime >= 5000)
        {
            lastChangeMoodTime = new Date().getTime();
            inSight = !inSight;
        }
    }

    @Override
    public void getHit()
    {
        if (this.inSight)
            super.getHit();
    }
}
