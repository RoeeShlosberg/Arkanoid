// Roee Shlosberg 211600812
package gamePlay.levels;

import gamePlay.screenDraw.Screen;
import geometry.axis.Velocity;
import geometry.objects.Block;

import java.util.List;

/**
 * The interface Level information - has all the information for the level initialization.
 */
public interface LevelInformation {
    /**
     * Number of balls int - how many balls in the level.
     *
     * @return the number.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list - The initial velocity of each ball.
     *
     * @return the list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int - how fast the paddle is.
     *
     * @return the num of speed.
     */
    int paddleSpeed();

    /**
     * Paddle width int - the paddle width.
     *
     * @return the width.
     */
    int paddleWidth();

    /**
     * Level name string - name that will be displayed at the top of the screen.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background - Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Screen getBackground();

    /**
     * Blocks list - he Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int - Number of blocks that should be removed.
     *
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();
}
