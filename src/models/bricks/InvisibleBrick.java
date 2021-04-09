package models.bricks;

public class InvisibleBrick extends GlassBrick
{

    InvisibleBrick(int x, int y)
    {
        super(x, y);
        this.visible = false;
        this.kind = "INVISIBLE";
    }
}
