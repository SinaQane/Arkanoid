package graphics;

import data.Save;
import logic.LogicalAgent;
import models.Ball;
import models.bricks.Brick;
import models.prizes.Prize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Panel extends JPanel implements ActionListener
{
    LogicalAgent logicalAgent;
    JFrame gameFrame;

    Action aKey;
    Action dKey;
    Action spaceKey;
    Action escKey;

    Timer timer;

    final int WIDTH = 1100;
    final int HEIGHT = 600;

    public Panel (LogicalAgent logicalAgent , JFrame frame)
    {
        this.logicalAgent = logicalAgent;
        timer = new Timer(10 , this);
        gameFrame = frame;

        this.setLayout(null);
        this.setPreferredSize(new Dimension(WIDTH , HEIGHT));
        this.setBackground(new Color(36, 43, 50));

        aKey = new LeftKey();
        dKey = new RightKey();
        spaceKey = new SpaceKey();
        escKey = new EscKey();

        this.getInputMap().put(KeyStroke.getKeyStroke('a') , "aAction");
        this.getActionMap().put("aAction", aKey);
        this.getInputMap().put(KeyStroke.getKeyStroke('d') , "dAction");
        this.getActionMap().put("dAction", dKey);
        this.getInputMap().put(KeyStroke.getKeyStroke(' ') , "spaceAction");
        this.getActionMap().put("spaceAction", spaceKey);
        this.getInputMap().put(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE) , "escapeAction");
        this.getActionMap().put("escapeAction", escKey);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        for(Ball ball : logicalAgent.gamePanel.getBalls())
        {
            if(ball.getFireBall())
                g2D.setColor(Color.orange);
            else
                g2D.setColor(Color.black);
            g2D.fillOval((int) ball.getX(), (int) ball.getY(), (int) (2*ball.getRadius()), (int) (2*ball.getRadius()));
        }

        for(Brick brick : logicalAgent.gamePanel.getBricks())
        {
            if (brick.getKind().equals("GLASS") && brick.getIntact())
            {
                g2D.setColor(new Color(184, 255, 255));
                g2D.fillRect((int)brick.getX() , (int)brick.getY() , (int)brick.getLength() , (int)brick.getHeight());
            }

            else if (brick.getKind().equals("BLINKING") && brick.getIntact() && brick.getInSight())
            {
                g2D.setColor(new Color(255, 213, 86));
                g2D.fillRect((int)brick.getX() , (int)brick.getY() , (int)brick.getLength() , (int)brick.getHeight());
            }

            else if (brick.getKind().equals("WOODEN") && brick.getIntact())
            {
                g2D.setColor(new Color(127, 103, 92));
                g2D.fillRect((int)brick.getX() , (int)brick.getY() , (int)brick.getLength() , (int)brick.getHeight());
            }
        }

        for (Prize prize : logicalAgent.gamePanel.getReleasedPrizes())
        {
            if(prize.getKind().equals("BIGGER_PAD"))
                g2D.setColor(Color.white);

            if(prize.getKind().equals("DIZZY_PAD"))
                g2D.setColor(Color.red);

            if(prize.getKind().equals("FASTER_BALL"))
                g2D.setColor(Color.magenta);

            if(prize.getKind().equals("FIREBALL"))
                g2D.setColor(Color.yellow);

            if(prize.getKind().equals("RANDOM"))
                g2D.setColor(Color.pink);

            if(prize.getKind().equals("SLOWER_BALL"))
                g2D.setColor(Color.cyan);

            if(prize.getKind().equals("SMALLER_PAD"))
                g2D.setColor(Color.darkGray);

            if(prize.getKind().equals("TRIPLE_BALL"))
                g2D.setColor(Color.gray);

            g2D.fillOval((int) prize.getX(), (int) prize.getY(), (int) (2*prize.getRadius()), (int) (2*prize.getRadius()));
        }
        g2D.setColor(Color.white);
        g2D.fillRect((int)logicalAgent.gamePanel.getPad().getX(),
                     (int)logicalAgent.gamePanel.getPad().getY(),
                (int)logicalAgent.gamePanel.getPad().getLength(),
                (int)logicalAgent.gamePanel.getPad().getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == timer)
        {
            if(logicalAgent.refreshObjects())
            {
                if(logicalAgent.gamePanel.getUser().getLives() <= 0)
                {
                    timer.stop();
                    gameFrame.dispose();
                    new EndFrame();
                }
                else
                {
                    logicalAgent.gamePanel.newBall();
                    repaint();
                    revalidate();
                }
            }
            else
            {
                repaint();
                revalidate();
            }
        }
    }

    public class RightKey extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            logicalAgent.gamePanel.getPad().moveRight();
        }
    }

    public class SpaceKey extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(timer.isRunning())
                timer.stop();
            else
                timer.start();
        }
    }

    public class EscKey extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            timer.stop();
            gameFrame.dispose();
            Save.updateScoreBoard(logicalAgent.gamePanel);
            new ExitFrame(logicalAgent);
        }
    }

    public class LeftKey extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            logicalAgent.gamePanel.getPad().moveLeft();
        }
    }
}