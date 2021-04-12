package logic;

import data.Load;
import graphics.GameFrame;
import models.Ball;
import models.GamePanel;
import models.bricks.Brick;
import models.prizes.Prize;

public class LogicalAgent
{
    public GamePanel gamePanel;

    public LogicalAgent(String userName, String gameName, boolean newGame)
    {
        this.gamePanel = loadGamePanel(userName, gameName, newGame);
        new GameFrame(this);
    }

    public GamePanel loadGamePanel(String userName, String gameName, boolean newGame)
    {
        GamePanel gamePanel = Load.newGame();
        gamePanel.setName(gameName);
        gamePanel.getUser().setName(userName);
        if (!newGame)
        {
            if (Load.oldGame(userName, gameName) != null)
                return Load.oldGame(userName, gameName);
            return gamePanel;
        }
        return gamePanel;
    }

    public boolean refreshObjects() // Refreshes everything except for Pad (which will be updated via ActionListener)
    {
        for (Ball ball : gamePanel.getBalls())
        {
            ball.moveBall();
            ball.deactivateFireBall();
        }

        for (Prize prize : gamePanel.getReleasedPrizes())
        {
            prize.movePrize();
        }

        for (Brick brick : gamePanel.getBricks())
        {
            if (brick.getKind().equals("BLINKING"))
                brick.blink();
        }

        for (Prize prize : gamePanel.getReleasedPrizes())
        {
            if (gamePanel.getPad().prizeCollision(prize))
            {
                prize.usePrize(gamePanel.getUser());
                gamePanel.getReleasedPrizes().remove(prize);
            }
        }

        gamePanel.getPad().deactivateDizzy();

        gamePanel.addRow();

        return checkForEndTurn();
    }

    public boolean checkForEndTurn() // Returns true when the user loses 1 life or the game ends
    {
        if (gamePanel.getUser().getLives() <= 0)
            return true;


        for (Brick brick : gamePanel.getBricks())
        {
            if (brick.getIntact() && brick.getY() > gamePanel.getHeight() - 100)
            {
                gamePanel.getUser().loseLife();
                return true;
            }
        }

        for (Ball ball : gamePanel.getBalls())
        {
            if (ball.getY() + 2*ball.getRadius() >= gamePanel.getHeight())
            {
                if (gamePanel.getBalls().size()==1)
                {
                    gamePanel.getUser().loseLife();
                    gamePanel.getBalls().remove(ball);
                    return true;
                }
                gamePanel.getBalls().remove(ball);
            }
        }
        return false;
    }
}