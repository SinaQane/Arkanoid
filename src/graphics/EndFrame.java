package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndFrame extends JFrame implements ActionListener {
    
    JLabel gameOver;
    private final JButton retryButton;
    
    public EndFrame()
    {
        gameOver = new JLabel("You've lost!");
        gameOver.setFont(new Font(null , Font.PLAIN , 15));

        retryButton = new JButton("Retry...");
        retryButton.addActionListener(this);
        retryButton.setFocusable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Over!");
        this.setLayout(new FlowLayout());
        this.add(gameOver);
        this.add(retryButton);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == retryButton)
        {
            this.dispose();
            new FirstFrame();
        }
    }
}
