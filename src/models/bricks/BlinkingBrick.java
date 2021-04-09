package models.bricks;

public class BlinkingBrick extends Brick
{
    boolean inSight;
    BlinkingBrick(int x, int y)
    {
        super(x, y);
        this.inSight = true;
        this.kind = "BLINK";
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
