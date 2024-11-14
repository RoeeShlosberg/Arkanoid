// Roee Shlosberg 211600812
package gamePlay.levels;

import gamePlay.screenDraw.Level2Screen;
import gamePlay.screenDraw.Screen;
import geometry.axis.Point;
import geometry.axis.Rectangle;
import geometry.axis.Velocity;
import geometry.objects.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level two - all the information of level two.
 */
public class LevelTwo implements LevelInformation {
    /**
     * Instantiates a new Level two - no arguments.
     */
    public LevelTwo() {
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(45 - (i * 10), 3); // creates a bow of balls.
            list.add(v);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 600;
    } // very wide.

    @Override
    public String levelName() {
        return "Easy Wide";
    }

    @Override
    public Screen getBackground() {
        return new Level2Screen(800, 600);
    } // the level's special background.

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        // adding new blocks at the same height stick to each other, changes the color every 2-3 blocks.
        double blockWidth = (800 - 40) / 15.0;
        // adding new blocks to be removed.
        for (int i = 0; i < 15; i++) {
            Rectangle r = new Rectangle(new Point(800 - 20 - ((i + 1) * blockWidth), 250), blockWidth, 25);
            Block b = new Block(r);
            if (i < 2) {
                b.setColor(Color.CYAN);
            } else if (i < 4) {
                b.setColor(Color.PINK);
            } else if (i < 6) {
                b.setColor(Color.BLUE);
            } else if (i < 9) {
                b.setColor(Color.GREEN);
            } else if (i < 11) {
                b.setColor(Color.YELLOW);
            } else if (i < 13) {
                b.setColor(Color.ORANGE);
            } else {
                b.setColor(Color.RED);
            }
            list.add(b);
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    } // 2 * 6 + 3.
}