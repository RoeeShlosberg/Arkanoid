// Roee Shlosberg 211600812
package gamePlay.screenDraw;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level 1 screen - a black screen with blue target that draws.
 * extends regular background - with plain color.
 */
public class Level1Screen extends Screen {
    /**
     * Instantiates a new Level 1 screen.
     *
     * @param width  the width
     * @param height the height
     */
    public Level1Screen(int width, int height) {
        super(Color.BLACK, width, height);
    } // black background.
    // draws blue circles and lines creates a target sign.
    @Override
    public void drawOn(DrawSurface d) {
            super.drawOn(d); // black background.
            d.setColor(Color.BLUE);
            d.drawCircle(400, 165, 125);
            d.drawCircle(400, 165, 95);
            d.drawCircle(400, 165, 65);
            d.drawLine(400, 0, 400, 140);
            d.drawLine(400, 190, 400, 308);
            d.drawLine(260, 165, 375, 165);
            d.drawLine(425, 165, 540, 165);
    }
}
