package models.prizes;

import models.Ball;
import models.User;
import models.bricks.Brick;

public class FasterBallPrize extends Prize
{
    public FasterBallPrize(Brick brick)
    {
        super(brick);
        this.kind = "FASTER_BALL";
    }

    @Override
    public void usePrize(User user)
    {
        for (Ball ball : user.getGamePanel().getBalls())
            ball.faster();
        user.setScore(user.getScore() + 5);
    }
}
