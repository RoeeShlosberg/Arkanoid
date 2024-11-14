// Roee Shlosberg 211600812
package gamePlay.score;

import biuoop.DrawSurface;
import gamePlay.levels.GameLevel;
import gamePlay.time.Sprite;
import generators.Counter;

import java.awt.Color;

/**
 * The type Score indicator - respobsible for score showing on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score of the game, is always updated by the game itself.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Add to game - adding the object (as a sprite) to a game.
     *
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    // Sprite:
    // drawing the score on top of the screen.
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 17, ("Score: " + this.score.getValue()), 17);
    }
    // time passed - nothing.
    @Override
    public void timePassed() {
    }
}
