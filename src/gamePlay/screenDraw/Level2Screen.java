// Roee Shlosberg 211600812
package gamePlay.screenDraw;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level 2 screen - a screen of a sun and beams on white background.
 * extends regular screen.
 */
public class Level2Screen extends Screen {
    /**
     * Instantiates a new Level 2 screen.
     *
     * @param width  the width
     * @param height the height
     */
    public Level2Screen(int width, int height) {
        super(Color.WHITE, width, height);
    } // white background.

    // draw a sun made of yellow circles and yellow beams.
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        d.setColor(new Color(235, 230, 180));
        d.fillCircle(150, 140, 60);
        for (double i = 0; i < 700; i += 8) {
            d.drawLine(150, 130, (int) i, 250);
        }
        d.setColor(new Color(235, 220, 70));
        d.fillCircle(150, 140, 50);
        d.setColor(new Color(255, 230, 50));
        d.fillCircle(150, 140, 40);
    }
}
