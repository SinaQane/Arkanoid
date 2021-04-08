package models.prizes;

import models.User;
import models.bricks.Brick;

public class SmallerPadPrize extends Prize
{
    SmallerPadPrize(Brick brick)
    {
        super(brick);
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getPad().smaller();
    }
}
