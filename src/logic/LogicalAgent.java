package logic;

import graphics.GraphicalAgent;
import models.GamePanel;

public class LogicalAgent
{
    private final GraphicalAgent graphicalAgent;
    private final GamePanel gamePanel;

    public LogicalAgent()
    {
        this.graphicalAgent = new GraphicalAgent(this);
        this.gamePanel = loadGamePanel();
    }

    private GamePanel loadGamePanel()
    {
        return null;
    }
}
