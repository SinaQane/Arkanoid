package graphics;

import data.Load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNameFrame extends JPanel implements ActionListener
{
    private final JFrame frame;
    private final JButton submitButton;
    private final JButton backButton;
    private final JTextField textField;

    public EnterNameFrame()
    {
        JLabel label = new JLabel("Enter your name:");
        label.setFont(new Font(null , Font.PLAIN , 15));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFocusable(false);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200 , 20));

        frame = new JFrame();
        frame.add(label);
        frame.add(textField);
        frame.add(submitButton);
        frame.add(backButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitButton)
        {
            String name = textField.getText();
            frame.dispose();
            new ChooseGameFrame(name, Load.getGames(name));
        }
        if(e.getSource() == backButton)
        {
            frame.dispose();
            new FirstFrame();
        }
    }
}
