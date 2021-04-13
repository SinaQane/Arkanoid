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

    @Override
    public void blink()
    {
        if (new Date().getTime() - lastChangeMoodTime >= 1000)
        {
            lastChangeMoodTime = new Date().getTime();
            inSight = !inSight;
        }
    }
}
