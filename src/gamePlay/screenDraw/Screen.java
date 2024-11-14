// Roee Shlosberg 211600812
package gamePlay.screenDraw;

import java.awt.Color;
import biuoop.DrawSurface;
import gamePlay.levels.GameLevel;
import gamePlay.time.Sprite;
import geometry.axis.Point;
import geometry.axis.Rectangle;


/**
 * The type Screen - an object that can draw for game background.
 */
public class Screen implements Sprite {
    private Color col;
    private Rectangle rec;

    /**
     * Instantiates a new Screen - with frame sizes information.
     *
     * @param col    the color
     * @param width  the width
     * @param height the height
     */
    public Screen(Color col, int width, int height) {
        this.col = col;
        this.rec = new Rectangle(new Point(0, 0), width, height);
    }

    /**
     * Instantiates a new Screen - with default color.
     *
     * @param width  the width
     * @param height the height
     */
    public Screen(int width, int height) {
        this.rec = new Rectangle(new Point(0, 0), width, height);
        this.col = Color.CYAN; // default pretty color.
    }

    /**
     * Add to game - adding the screen to the sprite list of a game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    // drawing the color by the rectangle size.
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.col);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    // don't change by time for now.
    @Override
    public void timePassed() {

    }
}
