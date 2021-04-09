package models.prizes;

import models.User;
import models.bricks.Brick;

public class SmallerPadPrize extends Prize
{
    public SmallerPadPrize(Brick brick)
    {
        super(brick);
        this.kind = "SMALLER_PAD";
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getPad().smaller();
    }
}
