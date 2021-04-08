package models.prizes;

import models.User;
import models.bricks.Brick;

public class DizzyPadPrize extends Prize
{
    DizzyPadPrize(Brick brick)
    {
        super(brick);
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getPad().activateDizzy();
    }
}
