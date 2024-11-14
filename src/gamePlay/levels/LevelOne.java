// Roee Shlosberg 211600812
package gamePlay.levels;

import gamePlay.screenDraw.Level1Screen;
import gamePlay.screenDraw.Screen;
import geometry.axis.Point;
import geometry.axis.Rectangle;
import geometry.axis.Velocity;
import geometry.objects.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level one - level one information.
 */
public class LevelOne implements LevelInformation {
    /**
     * Instantiates a new Level one - no arguments.
     */
    public LevelOne() {
    }
    @Override
    public int numberOfBalls() {
        return 1;
    } // one ball.

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(0, 3)); // ball's speed.
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Screen getBackground() {
        return new Level1Screen(800, 600); // the level's special background.
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>(); // new list.
        // adding new block to be removed.
        list.add(new Block(new Rectangle(new Point(385, 150), 30, 30), Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
