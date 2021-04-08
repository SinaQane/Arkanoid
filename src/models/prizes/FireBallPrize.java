package models.prizes;

import models.User;
import models.bricks.Brick;

public class FireBallPrize extends Prize
{
    FireBallPrize(Brick brick)
    {
        super(brick);
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getBall().activateFireBall();
    }
}
