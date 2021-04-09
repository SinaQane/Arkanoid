package models.bricks;

public class BlinkingBrick extends Brick
{
    boolean inSight;
    public BlinkingBrick(double x, double y)
    {
        super(x, y);
        this.inSight = true;
        this.kind = "BLINKING";
    }

    public void blink()
    {
        // TODO change inSight every n seconds
    }

    @Override
    public void getHit()
    {
        if (this.inSight) // TODO fix this in Ball class
            super.getHit();
    }
}
