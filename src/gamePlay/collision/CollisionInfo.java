// Roee Shlosberg 211600812
package gamePlay.collision;

import geometry.axis.Point;

/**
 * The type Collision info - contains the point and object of a specific hit.
 */
public class CollisionInfo {
    private Point point; // collosion point.

    private Collidable object; // hit object

    /**
     * Instantiates a new Collision info.
     *
     * @param p the point of collision.
     * @param c the collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.point = p;
        this.object = c;
    }

    /**
     * Collision point - returns point of the hit.
     *
     * @return the hitting point.
     */
    public Point collisionPoint() {
        return this.point;
    }

    /**
     * Collision object collidable - return the object.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}