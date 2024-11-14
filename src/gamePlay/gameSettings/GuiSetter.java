// Roee Shlosberg 211600812
package gamePlay.gameSettings;

import biuoop.GUI;

/**
 * The type Gui setter - have GUI object, frame sizes.
 */
public class GuiSetter {
    private GUI  gui;
    private int width;
    private int height;

    /**
     * Instantiates a new Gui setter.
     *
     * @param gui    the gui
     * @param width  the width
     * @param height the height
     */
    public GuiSetter(GUI gui, int width, int height) {
        this.gui = gui;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets gui - return the GUI object.
     *
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets width - return the frame width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height - return the frame height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
