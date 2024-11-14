// Roee Shlosberg 211600812
package gamePlay.removing;

import geometry.objects.Ball;
import geometry.objects.Block;

/**
 * The interface Hit listener - every object that is "interested" in hitting avenues.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the object (usually block) that is hit.
     * @param hitter   the hitting object (usually a ball).
     */
    void hitEvent(Block beingHit, Ball hitter);
}