package models.bricks;

public class InvisibleBrick extends GlassBrick
{

    public InvisibleBrick(double x, double y)
    {
        super(x, y);
        this.visible = false;
        this.kind = "INVISIBLE";
    }
}
