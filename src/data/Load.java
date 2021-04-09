package data;

import models.Ball;
import models.GamePanel;
import models.Pad;
import models.User;
import models.bricks.*;
import models.prizes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Load
{
    public GamePanel loadGame(String path)
    {
        try
        {
            File defaultBoard = new File(path);
            Scanner scanner = new Scanner(defaultBoard);

            int brickCounter = Integer.parseInt(scanner.nextLine().split(" ")[1]);
            List<Brick> bricks = new LinkedList<>();
            for (int i = 0; i < brickCounter; i++)
            {
                String[] brickArray = scanner.nextLine().split(" ");
                double x = Double.parseDouble(brickArray[0]);
                double y = Double.parseDouble(brickArray[1]);
                int lives = Integer.parseInt(brickArray[3]);

                Brick brick = null;

                switch (brickArray[2])
                {
                    case "GLASS":
                        brick = new GlassBrick(x, y);
                        break;
                    case "WOODEN":
                        brick = new WoodenBrick(x, y);
                        break;
                    case "INVISIBLE":
                        brick = new InvisibleBrick(x, y);
                        break;
                    case "BLINKING":
                        brick = new BlinkingBrick(x, y);
                        break;
                }

                assert brick != null;
                brick.setLives(lives);

                Prize prize = null;

                switch (brickArray[4])
                {
                    case "BIGGER_PAD":
                        prize = new BiggerPadPrize(brick);
                        break;
                    case "SMALLER_PAD":
                        prize = new SmallerPadPrize(brick);
                        break;
                    case "FASTER_BALL":
                        prize = new FasterBallPrize(brick);
                        break;
                    case "SLOWER_BALL":
                        prize = new SlowerBallPrize(brick);
                        break;
                    case "FIREBALL":
                        prize = new FireBallPrize(brick);
                        break;
                    case "DIZZY_PAD":
                        prize = new DizzyPadPrize(brick);
                        break;
                    case "TRIPLE_BALL":
                        prize = new TripleBallPrize(brick);
                        break;
                    case "RANDOM":
                        prize = new RandomPrize(brick);
                        break;
                }

                brick.setPrize(prize);
                bricks.add(brick);
            }

            scanner.nextLine();

            int ballCounter = Integer.parseInt(scanner.nextLine().split(" ")[1]);
            List<Ball> balls = new LinkedList<>();
            for (int i = 0; i < ballCounter; i++)
            {
                String[] ballArray = scanner.nextLine().split(" ");
                double x = Double.parseDouble(ballArray[0]);
                double y = Double.parseDouble(ballArray[1]);
                double dx = Double.parseDouble(ballArray[2]);
                double dy = Double.parseDouble(ballArray[3]);
                int fireBall = Integer.parseInt(ballArray[4]);
                long activationTime = Long.parseLong(ballArray[5]);
                Ball ball = new Ball();
                ball.setX(x);
                ball.setY(y);
                ball.setDx(dx);
                ball.setDy(dy);
                ball.setFireBall(fireBall == 1);
                ball.setActivationTime(activationTime);
                balls.add(ball);
            }

            scanner.nextLine();
            scanner.nextLine();

            String[] padArray = scanner.nextLine().split(" ");
            double x = Double.parseDouble(padArray[0]);
            double y = Double.parseDouble(padArray[1]);
            double length = Double.parseDouble(padArray[2]);
            int dizzy = Integer.parseInt(padArray[3]);
            long activationTime = Long.parseLong(padArray[4]);

            Pad pad = new Pad();
            pad.setX(x);
            pad.setY(y);
            pad.setLength(length);
            pad.setDizzy(dizzy == 1);
            pad.setActivationTime(activationTime);

            scanner.nextLine();
            scanner.nextLine();

            String[] userArray = scanner.nextLine().split(" ");
            String playerName = userArray[0];
            String gameName = userArray[1];
            int score = Integer.parseInt(userArray[2]);
            int lives = Integer.parseInt(userArray[3]);

            User user = new User(playerName);
            user.setScore(score);
            user.setLives(lives);

            return new GamePanel(gameName, user, bricks, balls, pad);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.err.println("Could not find default board file.");
            System.exit(-2);
        }
        return null;
    }

    public GamePanel newGame()
    {
        return loadGame("./resources/Default.Board");
    }

    public GamePanel oldGame(String user, String game)
    {
        if (getGameFile(user, game) != null)
            return loadGame("./resources/games" + user + "-" + game + ".Board");
        return null;
    }

    public static File getGameFile(String user, String game)
    {
        File gamesDir = new File("./resources/games");
        for (File file : Objects.requireNonNull(gamesDir.listFiles()))
        {
            if (file.getName().equals(user + "-" + game + ".Board"))
                return file;
        }
        return null;
    }

    public List<String> getGames(String user)
    {
        List<String> games = new LinkedList<>();
        File gamesDir = new File("./resources/games");
        for (File file : Objects.requireNonNull(gamesDir.listFiles()))
        {
            String[] splitPath = file.getPath().split("/");
            String userName = splitPath[splitPath.length - 1].split("-")[0];
            if (userName.equals(user))
                games.add(splitPath[splitPath.length - 1].split("-")[1].split("\\.")[0]);
        }
        return games;
    }

    public String loadScoreBoard()
    {
        return "";
    }
}
