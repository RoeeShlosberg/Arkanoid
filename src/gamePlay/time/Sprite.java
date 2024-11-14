// Roee Shlosberg 211600812
package gamePlay.time;

import biuoop.DrawSurface;

/**
 * The interface Sprite - for objects that change and draw while the game is running.
 */
public interface Sprite {
    /**
     * Draw on - draw the object in given draw-surface.
     *
     * @param d the draw-surface
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed - notify the sprite time has passes (for him to move etc.).
     */
    void timePassed();
}