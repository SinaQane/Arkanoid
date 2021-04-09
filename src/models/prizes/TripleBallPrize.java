package models.prizes;

import models.Ball;
import models.User;
import models.bricks.Brick;

import java.util.Random;

public class TripleBallPrize extends Prize
{
    public TripleBallPrize(Brick brick)
    {
        super(brick);
        this.kind = "TRIPLE_BALL";
    }

    @Override
    public void usePrize(User user)
    {
        Random random = new Random();
        Ball ball = user.getGamePanel().getBalls()
                .get(random.nextInt(user.getGamePanel().getBalls().size()));
        Ball firstBall = new Ball();
        Ball secondBall = new Ball();
        firstBall.setX(ball.getX());
        firstBall.setY(ball.getY());
        secondBall.setX(ball.getX());
        secondBall.setY(ball.getY());
        firstBall.setDx(ball.getDx()*0.8 + ball.getDy()*0.6);
        firstBall.setDy(ball.getDx()*0.6 + ball.getDy()*0.8);
        secondBall.setDx(- ball.getDx()*0.8 - ball.getDy()*0.6);
        secondBall.setDy(- ball.getDx()*0.6 - ball.getDy()*0.8);
        user.getGamePanel().getBalls().add(firstBall);
        user.getGamePanel().getBalls().add(secondBall);
    }
}
