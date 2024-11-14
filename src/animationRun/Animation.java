// Roee Shlosberg 211600812
package animationRun;

import biuoop.DrawSurface;

/**
 * The interface Animation - any class that paints a series of frames one after another.
 */
public interface Animation {
    /**
     * Do one frame - paint the next frame.
     *
     * @param d the draw-surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean - returns whether or not tha animation should stop.
     *
     * @return the boolean - true/false.
     */
    boolean shouldStop();
}