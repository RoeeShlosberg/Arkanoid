// Roee Shlosberg 211600812
package geometry.objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamePlay.levels.GameLevel;
import gamePlay.collision.Collidable;
import gamePlay.collision.CollisionInfo;
import gamePlay.collision.GameEnvironment;
import gamePlay.time.Sprite;
import geometry.axis.Velocity;
import geometry.axis.Line;
import geometry.axis.Point;
import geometry.axis.Rectangle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Paddle - an object that defined by rectangle shape and color, can be collided with and moved by keyboard.
 */
public class Paddle implements Sprite, Collidable {
    private GameEnvironment game; // a game list of collidable objects.
    private KeyboardSensor keyboard;
    private Color color;
    private Rectangle rec;
    private List<Ball> collideBalls; // list of the balls the paddle push when hits.
    private int speed; // the speed of the paddle - how much can go each time.

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param color    the color
     * @param rec      the rec
     * @param speed    the speed
     */
    public Paddle(KeyboardSensor keyboard, Color color, Rectangle rec, int speed) {
        this.keyboard = keyboard;
        this.color = color;
        this.rec = rec;
        this.collideBalls = new LinkedList<Ball>();
        this.game = null;
        this.speed = speed;
    }

    /**
     * Add game ball - adding a ball to the list of collidable balls.
     *
     * @param ball the ball
     */
    public void addGameBall(Ball ball) {
        this.collideBalls.add(ball);
    }

    /**
     * Move left - moving the paddle (setting rectangle), if it hits a ball it changes it's center.
     */
    public void moveLeft() {
        Point p = new Point(this.rec.getUpperLeft().getX() - this.speed, this.rec.getUpperLeft().getY());
        Line l = new Line(this.rec.getUpperLeft(), p);
        // finds the closest hit (which isn't the paddle itself) - if it does, then it won't pass it.
        CollisionInfo info = this.game.getClosestCollisionWithoutStart(l);
        if (info == null) { // no hit.
            this.rec.setUpperLeft(p);
        } else {
            this.rec.setUpperLeft(info.collisionPoint()); // moves until the hit.
        }
        for (Ball ball : this.collideBalls) { // each ball in the list.
            if (this.rec.isPointInside((ball).getPoint())) { // inside after the movement.
                ball.setPoint(new Point(this.rec.getUpperLeft().getX() - 1, (ball).getY()));
            }
        }
    }

    /**
     * Move right - moving the paddle (setting rectangle), if it hits a ball it changes it's center.
     */
    public void moveRight() {
        Point right = new Point(this.rec.getUpperLeft().getX() + this.rec.getWidth(), this.rec.getUpperLeft().getY());
        Point p = new Point(right.getX() + this.speed, right.getY());
        Line l = new Line(right, p);
        CollisionInfo info = this.game.getClosestCollisionWithoutStart(l);
        if (info == null) {
            this.rec.setUpperLeft(new Point(this.rec.getUpperLeft().getX() + 5, this.rec.getUpperLeft().getY()));
        } else {
            double distance = right.distance(info.collisionPoint());
            this.rec.setUpperLeft(new Point(this.rec.getUpperLeft().getX() + distance, right.getY()));
        }
        for (Ball ball : this.collideBalls) { // each ball in the list.
            if (this.rec.isPointInside((ball).getPoint())) { // inside after the movement.
                ball.setPoint(new Point(this.rec.getUpperLeft().getX() + this.rec.getWidth() + 1,
                        ball.getY()));
            }
        }
    }

    // Sprite methods:
    // time passed - moving the block right/left depend on the user clicks.
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    // draw on - drawing the block on a given sur-face.
    @Override
    public void drawOn(DrawSurface d) {
        // draw the rectangle:
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        // draw outlines:
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    // Collidable methods
    // returning the rectangle object of the paddle.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    // hit - returning the velocity calculated by the hit, depends on the direction.
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // current conditions:
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (collisionPoint.isInLine(this.rec.lineUp()) && dy > 0) { // hitting the paddle from above.
            return velocityOfFunPaddle(collisionPoint, currentVelocity);
        }
        // hitting from the left or right:
        return new Velocity(-dx, dy);
    }

    /**
     * Add to game - adding the paddle to a given game (adding to sprite and collidable lists).
     *
     * @param g the game.
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        this.game = g.getEnvironment();
    }

    // Angle from hit double - calculating an angle from hit (above), depends on the place in the paddle.
    private Velocity velocityOfFunPaddle(Point collisionPoint, Velocity currentVelocity) {
        // current values:
        Point upperLeft = this.rec.getUpperLeft();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);
        double fifth = this.rec.getWidth() / 5; // for each part of the paddle.
        if (collisionPoint.getX() <= upperLeft.getX() + fifth) { // first part.
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPoint.getX() <= upperLeft.getX() + fifth * 2) { // second part
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (collisionPoint.getX() <= upperLeft.getX() + fifth * 3) { // third part - normal bounce.
            return new Velocity(dx, -dy);
        } else if (collisionPoint.getX() <= upperLeft.getX() + fifth * 4) { // fifth part.
            return Velocity.fromAngleAndSpeed(30, speed);
        } else { // fifth part.
            return Velocity.fromAngleAndSpeed(60, speed);
        }
    }
}