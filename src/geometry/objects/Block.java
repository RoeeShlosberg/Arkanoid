// Roee Shlosberg 211600812
package geometry.objects;

import gamePlay.levels.GameLevel;
import gamePlay.collision.Collidable;
import gamePlay.removing.HitListener;
import gamePlay.removing.HitNotifier;
import gamePlay.time.Sprite;
import geometry.axis.Velocity;
import geometry.axis.Point;
import geometry.axis.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block - an object that defined by rectangle shape and color and can be collided with.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color col;
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Block without color.
     *
     * @param rect the rectangle of the block.
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.col = Color.GRAY; // default color.
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block with color.
     *
     * @param rect the rectangle of the block.
     * @param col  the color of the block.
     */
    public Block(Rectangle rect, Color col) {
        this.rect = rect;
        this.col = col;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Add to game - adding this object to a given game (adding to collidable and sprite lists).
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove from game - removes the block from a given game (Collidable and Sprite lists).
     *
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Sets color - changing the color of block.
     *
     * @param c the new color.
     */
    public void setColor(Color c) {
        this.col = c;
    }
    // HitNotifier methods:
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    // Collidable methods:
    // returning the rectangle object of the block.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    // calculating the velocity of the ball that hit the block - defined by horizontal / vertical lines.
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point upperLeft = this.rect.getUpperLeft();
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // if it hit a horizontal line - reverse the dy. making sure it hit from the sides and not diagonal.
        if ((collisionPoint.isInLine(this.rect.lineUp()) && dy > 0)
                || (collisionPoint.isInLine(this.rect.lineDown()) && dy < 0)) {
            dy *= -1;
        }
        // if it hit a vertical line - reverse the dx. making sure it hit from the sides and not diagonal.
        if ((collisionPoint.isInParallel(this.rect.lineLeft()) && dx > 0)
                || (collisionPoint.isInParallel(this.rect.lineRight()) && dx < 0)) {
            dx *= -1;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    // Sprite methods:
    // drawing the block in given sur-face.
    @Override
    public void drawOn(final biuoop.DrawSurface surface) {
        // draw the rectangle:
        surface.setColor(this.col);
        surface.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // draw outlines:
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }

    // time passed - nothing for now.
    @Override
    public void timePassed() {
    }

    // when there's hit - indicates to all listeners:
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
