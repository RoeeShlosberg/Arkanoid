// Roee Shlosberg 211600812
package gamePlay.collision;
import geometry.axis.Velocity;
import geometry.axis.Point;
import geometry.axis.Rectangle;
import geometry.objects.Ball;

/**
 * The interface Collidable - any object the can "hit" and return.
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle of the object (shape).
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity - calculate the velocity the ball gets after the collision.
     *
     * @param collisionPoint  the collision point of the hit.
     * @param currentVelocity the current velocity of the ball when hits.
     * @param hitter the hitting ball of the collision.
     * @return the velocity after it returns.
     */

    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}