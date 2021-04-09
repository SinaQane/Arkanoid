package models.prizes;

import models.Ball;
import models.User;
import models.bricks.Brick;

import java.util.Random;

public class RandomPrize extends Prize
{
    public RandomPrize(Brick brick)
    {
        super(brick);
        this.kind = "RANDOM";
    }

    @Override
    public void usePrize(User user)
    {
        Random random = new Random();
        int rnd = random.nextInt(7);
        switch (rnd)
        {
            case 0:
                user.getGamePanel().getPad().bigger();
                break;
            case 1:
                user.getGamePanel().getPad().smaller();
                break;
            case 2:
                for (Ball ball : user.getGamePanel().getBalls())
                    ball.faster();
                break;
            case 3:
                for (Ball ball : user.getGamePanel().getBalls())
                    ball.slower();
                break;
            case 4:
                for (Ball ball : user.getGamePanel().getBalls())
                    ball.activateFireBall();
                break;
            case 5:
                user.getGamePanel().getPad().activateDizzy();
                break;
            case 6:
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
                break;
        }
    }
}
