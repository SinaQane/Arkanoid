package models.balls;

import java.util.Date;

public class Ball
{
    int x;
    int y;
    int radius;
    double directionAngle;
    int speed;
    boolean fireBall;
    long activationTime;

    Ball()
    {

    }

    public void setFireBall(boolean active)
    {
        this.fireBall = active;
        this.activationTime = new Date().getTime();
    }

    public void move()
    {

    }
}
