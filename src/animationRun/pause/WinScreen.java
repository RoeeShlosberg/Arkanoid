// Roee Shlosberg 211600812
package animationRun.pause;

import animationRun.Animation;
import biuoop.DrawSurface;
import generators.Counter;

import java.awt.Color;


/**
 * The type Win screen - a screen of a game ending with a win.
 */
public class WinScreen implements Animation {
    private Counter score;

    /**
     * Instantiates a new Win screen.
     *
     * @param score the final game's score.
     */
    public WinScreen(Counter score) {
        this.score = score;
    }
    // text of "you win" + score.
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(250, d.getHeight() / 2, "You Win!", 50);
        d.drawText(250, d.getHeight() / 2 + 70, "Your score is " + this.score.getValue(), 50);
    }
    // default - always continue.
    @Override
    public boolean shouldStop() {
        return false;
    }
}
