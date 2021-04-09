package models.prizes;

import models.User;
import models.bricks.Brick;

public class BiggerPadPrize extends Prize
{

    BiggerPadPrize(Brick brick)
    {
        super(brick);
        this.kind = "BIGGER_PAD";
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getPad().bigger();
    }
}
