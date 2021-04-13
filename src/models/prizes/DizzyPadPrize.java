package models.prizes;

import models.User;
import models.bricks.Brick;

public class DizzyPadPrize extends Prize
{
    public DizzyPadPrize(Brick brick)
    {
        super(brick);
        this.kind = "DIZZY_PAD";
    }

    @Override
    public void usePrize(User user)
    {
        user.getGamePanel().getPad().activateDizzy();
        user.setScore(user.getScore() - 10);
    }
}
