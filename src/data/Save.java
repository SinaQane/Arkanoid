package data;

import models.Ball;
import models.GamePanel;
import models.Pad;
import models.bricks.Brick;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

public class Save
{
    public void saveGame(GamePanel gamePanel, String user, String game) throws FileNotFoundException
    {
        if (Load.getGameFile(user, game) != null)
        {
            if (Objects.requireNonNull(Load.getGameFile(user, game)).delete())
                System.out.println("File deleted.");
        }
        File gameFile = new File("./resources/games" + user + "-" + game + ".Board");
        PrintStream printStream = new PrintStream(gameFile);

        printStream.println("BRICKS[ " + gamePanel.getBricks().size() + " ]:");

        for (int i = 0; i < gamePanel.getBricks().size(); i++)
        {
            Brick brick = gamePanel.getBricks().get(i);
            printStream.println(brick.getX() + " " + brick.getY() + " " + brick.getKind()
                    + " " + brick.getLives() + " " + brick.getPrize().getKind());
        }

        printStream.println();

        printStream.println("BALLS[ " + gamePanel.getBalls().size() + " ]:");

        for (int i = 0; i < gamePanel.getBalls().size(); i++)
        {
            Ball ball = gamePanel.getBalls().get(i);
            int fireBall = 0;
            if (ball.getFireBall())
                fireBall = 1;
            printStream.println(ball.getX() + " " + ball.getY() + " " + ball.getDx() + " " + ball.getDy()
                    + " " + fireBall + ball.getActivationTime());
        }

        printStream.println();

        printStream.println("PAD[ 1 ]:");

        Pad pad = gamePanel.getPad();
        int dizzy = 0;
        if (pad.getDizzy())
            dizzy = 1;

        printStream.println(pad.getX() + " " + pad.getY() + " " + pad.getLength()
                + " " + dizzy + " " + pad.getActivationTime());

        printStream.println();

        printStream.println("INFO[ 1 ]:");

        printStream.println(gamePanel.getUser().getName() + " " + gamePanel.getName()
                + " " + gamePanel.getUser().getScore() + " " + gamePanel.getUser().getLives());
    }

    public void updateScoreBoard()
    {

    }
}
