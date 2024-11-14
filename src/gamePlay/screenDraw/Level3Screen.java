// Roee Shlosberg 211600812
package gamePlay.screenDraw;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level 3 screen - a background of a building on green background.
 * extends a regular plain screen.
 */
public class Level3Screen extends Screen {
    /**
     * Instantiates a new Level 3 screen.
     *
     * @param width  the width
     * @param height the height
     */
    public Level3Screen(int width, int height) {
        super(new Color(42, 129, 22), width, height);
    } // green in back.
    // draws a building made of grey different rectangles and white "windows", with red circles at the top.
    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d);
        // the squares:
        d.setColor(new Color(40, 40, 40));
        d.fillRectangle(60, 450, 99, 150);
        d.setColor(new Color(60, 60, 60));
        d.fillRectangle(93, 395, 33, 55);
        d.setColor(new Color(80, 80, 80));
        d.fillRectangle(104, 205, 11, 190);
        // the circles:
        d.setColor(new Color(214, 171, 102));
        d.fillCircle(110, 193, 12);
        d.setColor(Color.RED);
        d.fillCircle(110, 193, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(110, 193, 3);
        // the windows:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                double wallSize = 8.5;
                int windowWeight = 10;
                int windowHeight = 25;
                int x = (int) (((wallSize + windowWeight) * i) + 60 + wallSize);
                int y = (int) (((wallSize + windowHeight) * j) + 450 + wallSize);
                d.fillRectangle(x, y, windowWeight, windowHeight);
            }
        }
    }
}
