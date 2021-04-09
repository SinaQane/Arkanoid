package models.prizes;

import models.Ball;
import models.User;
import models.bricks.Brick;

public class FireBallPrize extends Prize
{
    public FireBallPrize(Brick brick)
    {
        super(brick);
        this.kind = "FIREBALL";
    }

    @Override
    public void usePrize(User user)
    {
        for (Ball ball : user.getGamePanel().getBalls())
            ball.activateFireBall();
    }
}
