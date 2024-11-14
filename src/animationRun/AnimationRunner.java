// Roee Shlosberg 211600812
package animationRun;

import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * The type Animation runner - runs an animation until it's over.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param gui             the gui that's runs the draw-surface & showing it.
     * @param framesPerSecond the frames per second (usually 60).
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Run - run the animation until returns should stop.
     *
     * @param animation the animation that'll run.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper(); // new sleeper for animation.
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) { // indicates stop!
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d); // another frame.
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
