// Roee Shlosberg 211600812
package geometry.objects;

import biuoop.DrawSurface;
import java.awt.Color;

import gamePlay.levels.GameLevel;
import gamePlay.collision.CollisionInfo;
import gamePlay.collision.GameEnvironment;
import gamePlay.time.Sprite;
import geometry.axis.Line;
import geometry.axis.Point;
import geometry.axis.Velocity;

/**
 * The object of a ball - a circle that can be displayed on screen.
 */
public class Ball implements Sprite {
    private GameEnvironment game; // a game list of collidable objects.
    private Point point;
    private int radius;
    private Color color;
    private Velocity v;

    /**
     * Instantiates a new Ball.
     *
     * @param center the center point
     * @param r      the radius
     * @param col    the color
     * @param vel    the velocity
     */
    public Ball(Point center, int r, Color col, Velocity vel) {
        this.point = center;
        this.radius = r;
        this.color = col;
        this.v = vel;
    }

    /**
     * Instantiates a new Ball with x&y point values.
     *
     * @param x   the x value of the center point
     * @param y   the y value of the center point
     * @param r   the radius
     * @param col the color
     * @param v   the v
     */
    public Ball(double x, double y, int r, Color col, Velocity v) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = col;
        this.v = v;
    }

    /**
     * Gets x - returns the x value of the ball center.
     *
     * @return the x value
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * Gets y - returns the y value of the ball center.
     *
     * @return the y value
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * Gets size - returns the radius.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color - returns the ball's color.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets point - returns the center point.
     *
     * @return the point
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Sets point.
     *
     * @param p the p
     */
    public void setPoint(Point p) {
        this.point = p;
    }

    /**
     * Move one step - moving the circle (by center point) by the speed of his.
     * if the ball hits his borders - reverse his direction by hit velocity method.
     */
    public void moveOneStep() {
        Point p = this.getVelocity().applyToPoint(this.point); // the new point
        Line l = new Line(this.point, p); // the path of the ball.
        CollisionInfo info = this.game.getClosestCollision(l);
        if (info == null) { // hit nothing.
            this.point = p;
            return;
        }
        this.point = this.point.applyToCloseCollisionPoint(info.collisionPoint()); // getting closer to the hit.
        // directing the ball by the object hit:
        this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
    }

    /**
     * Add to game - adding the ball to a given game (adding to a sprite list).
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.game = g.getEnvironment();
    }

    /**
     * Remove from game - removes the ball from a given game (from Sprite list).
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    // Sprite methods;
    // Draw on - drawing the circle colored on a given surface.
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }
    // time passed - moving the ball forward.
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets velocity - set a new given velocity to the ball.
     *
     * @param vel the new velocity.
     */
    public void setVelocity(Velocity vel) {
        this.v = vel;
    }

    /**
     * Sets game - setting a game environment (Collidable list) to the ball.
     *
     * @param game the game environment.
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }
}
