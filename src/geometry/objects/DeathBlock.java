// Roee Shlosberg 211600812
package geometry.objects;

import geometry.axis.Rectangle;

import java.awt.Color;


/**
 * The type Death Block - a block in which when a ball hits - he's gone from a game.
 */
public class DeathBlock extends Block {
    /**
     * Instantiates a new Ending Block without color - default of dark red.
     *
     * @param rect the rectangle of the block.
     */
    public DeathBlock(Rectangle rect) {
        super(rect, new Color(120, 0, 0));
    }

    /**
     * Instantiates a new Ending Block with color.
     *
     * @param rect the rectangle of the block.
     * @param col  the color of the block.
     */
    public DeathBlock(Rectangle rect, Color col) {
        super(rect, col);
    }
}

