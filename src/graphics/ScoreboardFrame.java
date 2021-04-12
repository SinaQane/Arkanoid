package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardFrame extends JFrame implements ActionListener
{
    private final JFrame frame;
    private final JButton backButton;
    ScoreboardFrame(String[] scores)
    {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Scoreboard: ");
        label.setFont(new Font(null , Font.BOLD , 16));

        panel.add(label, BorderLayout.NORTH);

        Box box = Box.createVerticalBox();

        if (scores.length == 0)
        {
            JLabel empty = new JLabel("The list is empty...");
            empty.setFont(new Font(null , Font.PLAIN , 15));
            box.add(empty);
        }
        else
        {
            for (String score : scores)
            {
                JLabel scoreLabel = new JLabel(score);
                scoreLabel.setFont(new Font(null, Font.PLAIN, 15));
                box.add(scoreLabel);
            }
        }

        panel.add(box, BorderLayout.CENTER);

        panel.add(backButton, BorderLayout.SOUTH);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Scoreboard");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == backButton)
        {
            frame.dispose();
            new FirstFrame();
        }
    }
}