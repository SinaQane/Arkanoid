package graphics;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    JFrame frame;
    Panel panel;

    public GameFrame(LogicalAgent logicalAgent)
    {
        frame = new JFrame();
        panel = new Panel(logicalAgent, frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Arkanoid");
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}