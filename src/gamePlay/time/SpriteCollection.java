// Roee Shlosberg 211600812
package gamePlay.time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import biuoop.DrawSurface;


/**
 * The type Sprite collection - contains a list of sprites in a game.
 */
public class SpriteCollection {
    private List<Sprite> spriteList; // linked list for adding new sprite.

    /**
     * Instantiates a new Sprite collection - initialize new list.
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<>();
    }

    /**
     * Gets size - returns the amount of objects contained.
     *
     * @return the size of the list.
     */
    public int getSize() {
        return this.spriteList.size();
    }

    /**
     * Add sprite to the list.
     *
     * @param s the new sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove sprite from the list.
     *
     * @param s the sprite removed.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Notify all time passed - call time-passed methods for all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList); // new copy.
        // Notify all listeners about a hit event:
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on - call drawing methods for all sprites.
     *
     * @param d the draw-surface given.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}