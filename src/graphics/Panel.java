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

public class Panel extends JPanel implements ActionListener
{
    LogicalAgent logicalAgent;
    JFrame gameFrame;

    JButton startButton;
    JButton exitButton;

    Action aKey;
    Action dKey;

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

        startButton = new JButton("Start/Pause");
        startButton.setFont(new Font(null , Font.PLAIN , 8));
        startButton.setForeground(Color.black);
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBounds(0 , 580 , 80 , 20);
        startButton.setBackground(new Color(60, 80, 111));

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font(null , Font.PLAIN , 8));
        exitButton.setForeground(Color.black);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);
        exitButton.setBounds(80 , 580 , 80 , 20);
        exitButton.setBackground(new Color(60, 80, 111));

        this.add(startButton);
        this.add(exitButton);

        aKey = new LeftKey();
        dKey = new RightKey();

        this.getInputMap().put(KeyStroke.getKeyStroke('a') , "aAction");
        this.getActionMap().put("aAction" , aKey);
        this.getInputMap().put(KeyStroke.getKeyStroke('d') , "dAction");
        this.getActionMap().put("dAction" , dKey);
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

        if(e.getSource() == exitButton)
        {
            timer.stop();
            gameFrame.dispose();
            Save.updateScoreBoard(logicalAgent.gamePanel);
            new ExitFrame(this.logicalAgent);
        }

        if(e.getSource() == startButton)
        {
            if(timer.isRunning())
                timer.stop();
            else
                timer.start();
        }
    }

    public class RightKey extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            logicalAgent.gamePanel.getPad().moveRight();
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