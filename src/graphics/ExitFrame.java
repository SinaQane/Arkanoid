package graphics;

import data.Save;
import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitFrame extends JFrame implements ActionListener {

    private final LogicalAgent logicalAgent;
    private final JFrame frame;
    private final JTextField enterNameField;
    private final JButton submitButton;

    public ExitFrame(LogicalAgent logicalAgent)
    {
        this.logicalAgent = logicalAgent;
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Save Game");
        frame.setLayout(new FlowLayout());

        JLabel text = new JLabel("Enter a name to save your game with: ");
        text.setFont(new Font(null , Font.PLAIN , 15));
        text.setVerticalTextPosition(JLabel.TOP);

        enterNameField = new JTextField();
        enterNameField.setPreferredSize(new Dimension(130 , 30));

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        frame.add(text);
        frame.add(enterNameField);
        frame.add(submitButton);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitButton)
        {
            Save.saveGame(logicalAgent.gamePanel, logicalAgent.gamePanel.getUser().getName(), enterNameField.getText());
            Save.updateScoreBoard(logicalAgent.gamePanel);
            frame.dispose();
        }
    }
}
