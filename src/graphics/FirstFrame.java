package graphics;

import data.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FirstFrame extends JFrame implements ActionListener
{
    private final JFrame frame;
    private final JButton enterButton;
    private final JButton scoreboardButton;

    public FirstFrame()
    {
        JLabel label = new JLabel("Please choose between these options:");
        label.setFont(new Font(null , Font.PLAIN , 15));

        enterButton = new JButton("Enter the Game");
        enterButton.addActionListener(this);
        enterButton.setFocusable(false);

        scoreboardButton = new JButton("Scoreboard");
        scoreboardButton.addActionListener(this);
        scoreboardButton.setFocusable(false);

        frame = new JFrame();
        frame.add(label);
        frame.add(enterButton);
        frame.add(scoreboardButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == enterButton)
        {
            frame.dispose();
            new EnterNameFrame();
        }
        else if (e.getSource() == scoreboardButton)
        {
            frame.dispose();
            String[] scoreBoard = null;
            try
            {
                scoreBoard = Load.loadScoreBoard();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            new ScoreboardFrame(scoreBoard);
        }
    }
}
