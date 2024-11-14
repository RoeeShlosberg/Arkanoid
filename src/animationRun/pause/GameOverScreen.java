// Roee Shlosberg 211600812
package animationRun.pause;

import animationRun.Animation;
import biuoop.DrawSurface;
import generators.Counter;

import java.awt.Color;


/**
 * The type Game over screen - present the ending of a losing game.
 */
public class GameOverScreen implements Animation {
    private Counter score;

    /**
     * Instantiates a new Game over screen.
     *
     * @param score the final score.
     */
    public GameOverScreen(Counter score) {
        this.score = score;
    }
    // black text - "game over" + score.
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(250, d.getHeight() / 2, "Game Over.", 50);
        d.drawText(250, d.getHeight() / 2 + 70, "Your score is " + this.score.getValue(), 50);
    }
    // default - always continue.
    @Override
    public boolean shouldStop() {
        return false;
    }
}