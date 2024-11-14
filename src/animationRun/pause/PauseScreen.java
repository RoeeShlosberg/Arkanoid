// Roee Shlosberg 211600812
package animationRun.pause;

import animationRun.Animation;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Pause screen - in case 'p' is pressed, freezing the game.
 */
public class PauseScreen implements Animation {
    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }
    // text of "pause'.
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(250, d.getHeight() / 2, "Pause:", 40);
        d.drawText(250, d.getHeight() / 2 + 70, "Press space to continue", 40);
    }
    // default - always continue.
    @Override
    public boolean shouldStop() {
        return false;
    }
}