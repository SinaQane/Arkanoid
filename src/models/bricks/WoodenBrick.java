package models.bricks;

public class WoodenBrick extends Brick
{
    public WoodenBrick(double x, double y)
    {
        super(x, y);
        this.lives = 2;
        this.kind = "WOODEN";
    }
}
