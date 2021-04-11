package graphics;

import logic.LogicalAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class ChooseGameFrame extends JFrame implements ActionListener
{
    private final JFrame frame;
    private final JComboBox<Object> gamesList;
    private final JButton submitButton;
    private final String playerName;

    ChooseGameFrame(String playerName, List<String> games)
    {
        this.playerName = playerName;
        frame = new JFrame();

        games.add(0, "New Game");

        JLabel text = new JLabel("Choose a game:");
        text.setFont(new Font(null , Font.PLAIN , 15));
        text.setHorizontalAlignment(JLabel.LEFT);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFocusable(false);

        this.gamesList = new JComboBox<>(games.toArray());

        frame.add(text);
        frame.add(gamesList);
        frame.add(submitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Choose Game");
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitButton)
        {
            String game = Objects.requireNonNull(gamesList.getSelectedItem()).toString();
            LogicalAgent logicalAgent = null;
            if (game.equals("New Game"))
                logicalAgent = new LogicalAgent(playerName, "GAME_NAME", true);
            else
                logicalAgent = new LogicalAgent(playerName, game, false);
            // TODO use logicalAgent to make a GameFrame(LogicalAgent logicalAgent);
            frame.dispose();
        }
    }
}
