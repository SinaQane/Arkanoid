package models.prizes;

import models.Ball;
import models.User;
import models.bricks.Brick;

public class SlowerBallPrize extends Prize
{
    public SlowerBallPrize(Brick brick)
    {
        super(brick);
        this.kind = "SLOWER_BALL";
    }

    @Override
    public void usePrize(User user)
    {
        for (Ball ball : user.getGamePanel().getBalls())
            ball.slower();
    }
}
