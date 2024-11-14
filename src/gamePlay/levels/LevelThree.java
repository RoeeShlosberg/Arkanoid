// Roee Shlosberg 211600812
package gamePlay.levels;

import gamePlay.screenDraw.Level3Screen;
import gamePlay.screenDraw.Screen;
import generators.RandomObjects;
import geometry.axis.Point;
import geometry.axis.Rectangle;
import geometry.axis.Velocity;
import geometry.objects.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level three - all the information for the level 3.
 */
public class LevelThree implements LevelInformation {
    /**
     * Instantiates a new Level three - no arguments.
     */
    public LevelThree() {
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Velocity v = RandomObjects.generateRandomVelocity(5); // two fast balls.
            list.add(v);
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    } // very fast paddle.

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Screen getBackground() {
        return new Level3Screen(800, 600);
    } // the level's special background.

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int blockWidth = 50;
        int blockHeight = 23;
    // same organization like past projects.
        // adding new blocks to be removed.
        for (int i = 0; i < 5; i++) {
            Color col = RandomObjects.generateRandomColor(); // random color.
            for (int j = 0; j < 10 - i; j++) {
                // from right -> left along the row
                double x = 800 - 20 - blockWidth - (j * blockWidth);
                double y = i * blockHeight + 150; // from up -> down every row
                Rectangle r = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block b = new Block(r, col);
                list.add(b);
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}