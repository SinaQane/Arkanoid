package models.bricks;

public class BlinkingBrick extends Brick
{
    boolean inSight;
    BlinkingBrick(int x, int y)
    {
        super(x, y);
        this.inSight = true;
    }

    public void blink()
    {

    }

    @Override
    public void getHit()
    {
        if (this.inSight)
            super.getHit();
    }
}
