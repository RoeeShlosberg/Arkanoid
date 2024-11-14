// Roee Shlosberg 211600812
package gamePlay.collision;

import geometry.axis.Line;
import geometry.axis.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Game environment - contains a list of Collidable objects.
 */
public class GameEnvironment {
    private List<Collidable> collidableList; // linked list for adding new objects.

    /**
     * Instantiates a new Game environment - new list.
     */
    public GameEnvironment() {
        this.collidableList = new LinkedList<>();
    }

    /**
     * Gets the closest collision - for a line, find all the object it hits,
     * and then returns the closest one to the start. in a case of no collisions - returns null.
     *
     * @param trajectory the line (start and end of ball track).
     * @return the closest collision info - point and object, or null if it doesn't hit.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.size() == 0) { // no collidable objects in this environment - no hits.
            return null;
        }
        // initialize point, object and distance to point for null.
        Point hitPoint = null;
        double minDistance = 0;
        Collidable hitCollidable = null;
        List<Collidable> collidables = new ArrayList<Collidable>(this.collidableList); // new copy.
        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) { // there's a hit
                double tempDistance = p.distance(trajectory.getStart());
                if (hitPoint == null || tempDistance < minDistance) { // first or closest hit to the start
                    minDistance = tempDistance;
                    hitPoint = p;
                    hitCollidable = c;
                } else if (tempDistance == minDistance) { // there is another exact hit - meaning two linking blocks:
                    // check if it is the right block, means that it's from his center:
                    if ((new Line(trajectory.getStart(), p)).isFromCenter(c.getCollisionRectangle())) {
                        minDistance = tempDistance;
                        hitPoint = p;
                        hitCollidable = c;
                    }
                }
            }
        }
        if (hitPoint == null) { // no hits in general.
            return null;
        }
        return new CollisionInfo(hitPoint, hitCollidable);
    }

    /**
     * Gets the closest collision - for a line, find all the object it hits besides the start of the line,
     * and then returns the closest one to the start. in a case of no collisions - returns null.
     *
     * @param trajectory the line (start and end of ball track).
     * @return the closest collision info - point and object, or null if it's not hit anything after the start.
     */
    public CollisionInfo getClosestCollisionWithoutStart(Line trajectory) {
        if (this.collidableList.size() == 0) { // no collidable objects in this environment - no hits.
            return null;
        }
        // initialize point, object and distance to point for null.
        Point hitPoint = null;
        double minDistance = 0;
        Collidable hitCollidable = null;
        List<Collidable> collidables = new ArrayList<Collidable>(this.collidableList); // new copy.
        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null && p != trajectory.getStart()) { // there's a hit
                double tempDistance = p.distance(trajectory.getStart());
                if (hitPoint == null || tempDistance < minDistance) { // first or closest hit to the start
                    minDistance = tempDistance;
                    hitPoint = p;
                    hitCollidable = c;
                }
            }
        }
        if (hitPoint == null) { // no hits in general.
            return null;
        }
        return new CollisionInfo(hitPoint, hitCollidable);
    }

    /**
     * Add collidable - adding a collidable to the list.
     *
     * @param c the collidable object.
     */
    public void addCollidable(Collidable c) {
        if (c != null) {
            collidableList.add(c);
        }
    }

    /**
     * Remove collidable - deleting a collidable object (if exist) in the list.
     *
     * @param c the collidable object.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }
}